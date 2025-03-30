package com.example.modyopokemon.dto.pokeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonDetail {
    private Integer id;
    private Integer weight;
    private String name;
    private List<PokemonTypes> types;
    private List<PokemonAbilities> abilities;
    private PokemonSprites sprites;
    private PokemonSpecieResource species;

    public PokemonSprites getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSprites pokemonSprites) {
        this.sprites = pokemonSprites;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PokemonTypes> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonTypes> types) {
        this.types = types;
    }

    public List<PokemonAbilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<PokemonAbilities> abilities) {
        this.abilities = abilities;
    }

    public PokemonSpecieResource getSpecies() {
        return species;
    }

    public void setSpecies(PokemonSpecieResource pokemonSpecieResource) {
        this.species = pokemonSpecieResource;
    }
}
