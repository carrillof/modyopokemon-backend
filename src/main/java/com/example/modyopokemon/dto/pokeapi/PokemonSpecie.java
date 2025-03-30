package com.example.modyopokemon.dto.pokeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonSpecie {
    private Integer id;
    private PokemonEvolutionChainResource evolution_chain;
    private List<FlavorTextEntry> flavor_text_entries;

    public PokemonEvolutionChainResource getEvolution_chain() {
        return evolution_chain;
    }

    public void setEvolution_chain(PokemonEvolutionChainResource evolution_chain) {
        this.evolution_chain = evolution_chain;
    }

    public List<FlavorTextEntry> getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public void setFlavor_text_entries(List<FlavorTextEntry> flavor_text_entries) {
        this.flavor_text_entries = flavor_text_entries;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
