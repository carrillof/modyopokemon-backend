package com.example.modyopokemon.dto;

public class CustomPokemonDetail {
    private String description;

    public CustomPokemonDetail(String description, CustomPokemonEvolution evolutionChain) {
        this.description = description;
        this.evolutionChain = evolutionChain;
    }

    private CustomPokemonEvolution evolutionChain;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CustomPokemonEvolution getEvolutionChain() {
        return evolutionChain;
    }

    public void setEvolutionChain(CustomPokemonEvolution evolutionChain) {
        this.evolutionChain = evolutionChain;
    }
}
