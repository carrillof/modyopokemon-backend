package com.example.modyopokemon.dto.pokeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonList {
    private Integer count;
    private String next;
    private String previous;
    private List<PokemonResource> results;

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

    public List<PokemonResource> getResults() {
        return results;
    }

    public void setResults(List<PokemonResource> results) {
        this.results = results;
    }
}
