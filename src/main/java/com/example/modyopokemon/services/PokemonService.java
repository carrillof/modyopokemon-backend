package com.example.modyopokemon.services;

import com.example.modyopokemon.dto.CustomPokemon;
import com.example.modyopokemon.dto.CustomPokemonDetail;
import com.example.modyopokemon.dto.CustomPokemonEvolution;
import com.example.modyopokemon.dto.CustomPokemonList;
import com.example.modyopokemon.dto.pokeapi.*;
import com.example.modyopokemon.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@org.springframework.stereotype.Service
public class PokemonService {
    private static final Logger log = LoggerFactory.getLogger(PokemonService.class);
    @Value("${spring.pokeapi-url}")
    private String pokeApiBaseUrl;
    private final RestTemplate restTemplate;

    public PokemonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("pokemonCache")
    public CustomPokemonList getDetailedPokemonList(int offset, int limit) {
        PokemonList pokemonList = getPokemonList(offset, limit);
        List<CustomPokemon> detailedPokemonList = new ArrayList<>();
        List<CompletableFuture<PokemonDetail>> pokemonDetailsFutureList = new ArrayList<>();

        for(PokemonResource pokemonResource : pokemonList.getResults()) {
            pokemonDetailsFutureList.add(getPokemon(pokemonResource.getUrl()));
        }
        CompletableFuture.allOf(pokemonDetailsFutureList.toArray(new CompletableFuture[0])).join();
        for(CompletableFuture<PokemonDetail> pokemonDetailFuture : pokemonDetailsFutureList) {
            detailedPokemonList.add(parsePokemonDetail(pokemonDetailFuture.join()));
        }

        return new CustomPokemonList(
                pokemonList.getCount(),
                parsePokeApiUrl(pokemonList.getNext()),
                parsePokeApiUrl(pokemonList.getPrevious()),
                detailedPokemonList
        );
    }

    @Cacheable("pokemonCache")
    public CustomPokemonDetail getPokemonDetail(int specieId) {
        PokemonSpecie pokemonSpecie = getPokemonSpecie(specieId);
        PokemonEvolutionChain pokemonEvolutionChain = getPokemonEvolutionChain(pokemonSpecie.getEvolution_chain().getUrl());
        return new CustomPokemonDetail(
                getFirstPokemonDescriptionByLanguage("en", pokemonSpecie.getFlavor_text_entries()),
                parsePokemonEvolutionChain(pokemonEvolutionChain.getChain())
        );
    }

    private PokemonList getPokemonList(int offset, int limit) {
        String url = pokeApiBaseUrl + "/pokemon" + "?offset=" + offset + "&limit=" + limit;
        log.info("Requesting pokemon list: {}", url);
        return restTemplate.getForObject(url, PokemonList.class);
    }

    public PokemonSpecie getPokemonSpecie(int specieId) {
        String url = pokeApiBaseUrl + "/pokemon-species/" + specieId;
        log.info("Requesting pokemon specie: {}", url);
        return restTemplate.getForObject(url, PokemonSpecie.class);
    }

    public PokemonEvolutionChain getPokemonEvolutionChain(String url) {
        log.info("Requesting pokemon evolution chain: {}", url);
        return restTemplate.getForObject(url, PokemonEvolutionChain.class);
    }

    @Async
    public CompletableFuture<PokemonDetail> getPokemon(String url) {
        log.info("Requesting pokemon: {}", url);
        return CompletableFuture.completedFuture(restTemplate.getForObject(url, PokemonDetail.class));
    }

    private String parsePokeApiUrl(String pokeApiUrl) {
        if(pokeApiUrl == null) {
            return null;
        }
        String parsedUrl = pokeApiUrl.replace(pokeApiBaseUrl, Utilities.getCurrentBaseUrl());
        if(parsedUrl.endsWith("/")) {
            return parsedUrl.substring(0, parsedUrl.length() - 1);
        } else {
            return parsedUrl;
        }
    }

    private CustomPokemon parsePokemonDetail(PokemonDetail pokemonDetail) {
        List<String> types = new ArrayList<>();
        List<String> abilities = new ArrayList<>();

        for(PokemonTypes type : pokemonDetail.getTypes()) {
            types.add(type.getType().getName());
        }

        for(PokemonAbilities ability : pokemonDetail.getAbilities()) {
            abilities.add(ability.getAbility().getName());
        }

        return new CustomPokemon(
                pokemonDetail.getId(),
                pokemonDetail.getWeight(),
                pokemonDetail.getName(),
                types, abilities,
                pokemonDetail.getSprites().getFront_default(),
                parsePokeApiUrl(pokemonDetail.getSpecies().getUrl())
        );
    }

    private String getFirstPokemonDescriptionByLanguage(String language, List<FlavorTextEntry> flavorTextEntries) {
        return flavorTextEntries.stream().filter(
                entry -> entry.getLanguage().getName().equals(language)
        ).findFirst().map(FlavorTextEntry::getFlavor_text).orElse(null);
    }

    private CustomPokemonEvolution parsePokemonEvolutionChain(PokemonEvolution chain) {
        CustomPokemonEvolution customPokemonEvolution = new CustomPokemonEvolution();
        customPokemonEvolution.setName(chain.getSpecies().getName());
        List<CustomPokemonEvolution> customPokemonEvolutions = new ArrayList<>();
        for(PokemonEvolution pokemonEvolution : chain.getEvolves_to()) {
            customPokemonEvolutions.add(parsePokemonEvolutionChain(pokemonEvolution));
        }
        customPokemonEvolution.setEvolvesTo(customPokemonEvolutions);
        return customPokemonEvolution;
    }
}
