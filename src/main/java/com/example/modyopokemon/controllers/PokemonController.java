package com.example.modyopokemon.controllers;

import com.example.modyopokemon.dto.CustomPokemonDetail;
import com.example.modyopokemon.dto.CustomPokemonList;
import com.example.modyopokemon.services.PokemonService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class PokemonController {
    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/pokemon")
    public CustomPokemonList getPokemonList(
            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "Offset must be greater than or equal to zero")
            @Max(value = 1500, message = "Offset must not be greater than 1500")
            int offset,
            @RequestParam(defaultValue = "20")
            @Min(value = 1, message = "Limit must be greater than or equal to one")
            @Max(value = 1500, message = "Offset must not be greater than 1500")
            int limit
    ) {
        return pokemonService.getDetailedPokemonList(offset, limit);
    }

    @GetMapping("/pokemon-species/{specieId}")
    public CustomPokemonDetail getPokemonDetail(
            @PathVariable
            @Min(value = 1, message = "specieId must be greater than or equal to one")
            @Max(value = 1500, message = "Offset must not be greater than 1500")
            int specieId
    ) {
        return pokemonService.getPokemonDetail(specieId);
    }
}
