package com.example.modyopokemon.dto;

import java.util.List;

public class CustomPokemon {
    private Integer id;
    private Integer weight;
    private String name;
    private List<String> types;
    private List<String> abilities;
    private String sprite;
    private String specie;

    public CustomPokemon(
            Integer id, Integer weight, String name, List<String> types, List<String> abilities, String sprite, String specie
    ) {
        this.id = id;
        this.weight = weight;
        this.name = name;
        this.types = types;
        this.abilities = abilities;
        this.sprite = sprite;
        this.specie = specie;
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

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }
}
