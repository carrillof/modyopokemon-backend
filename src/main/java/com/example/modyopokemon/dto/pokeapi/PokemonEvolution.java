package com.example.modyopokemon.dto.pokeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonEvolution {
    private PokemonSpecieResource species;
    private List<PokemonEvolution> evolves_to;

    public PokemonSpecieResource getSpecies() {
        return species;
    }

    public void setSpecies(PokemonSpecieResource species) {
        this.species = species;
    }

    public List<PokemonEvolution> getEvolves_to() {
        return evolves_to;
    }

    public void setEvolves_to(List<PokemonEvolution> evolves_to) {
        this.evolves_to = evolves_to;
    }
}
