package com.example.modyopokemon.dto.pokeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonEvolutionChain {
    private Integer id;
    private PokemonEvolution chain;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PokemonEvolution getChain() {
        return chain;
    }

    public void setChain(PokemonEvolution chain) {
        this.chain = chain;
    }
}
