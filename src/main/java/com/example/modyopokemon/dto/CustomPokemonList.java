package com.example.modyopokemon.dto;

import java.util.List;

public class CustomPokemonList {
    private Integer count;
    private String next;
    private String previous;
    private List<CustomPokemon> results;

    public CustomPokemonList(Integer count, String next, String previous, List<CustomPokemon> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<CustomPokemon> getResults() {
        return results;
    }

    public void setResults(List<CustomPokemon> results) {
        this.results = results;
    }
}
