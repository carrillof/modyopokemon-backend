package com.example.modyopokemon.dto;

import java.util.List;

public class CustomPokemonEvolution {
    private String name;
    private List<CustomPokemonEvolution> evolvesTo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CustomPokemonEvolution> getEvolvesTo() {
        return evolvesTo;
    }

    public void setEvolvesTo(List<CustomPokemonEvolution> evolvesTo) {
        this.evolvesTo = evolvesTo;
    }
}
