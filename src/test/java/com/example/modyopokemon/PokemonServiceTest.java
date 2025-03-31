package com.example.modyopokemon;

import com.example.modyopokemon.dto.CustomPokemonDetail;
import com.example.modyopokemon.dto.CustomPokemonList;
import com.example.modyopokemon.services.PokemonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PokemonServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    @Autowired
    private PokemonService pokemonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPokemonList() throws IOException {
        String pokemonListMockResponse = TestUtils.readFileAsString(
                "src/test/resources/pokemonListMockResponse.json"
        );
        String pokemonMockResponse = TestUtils.readFileAsString(
                "src/test/resources/pokemonMockResponse.json"
        );
        when(restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon", String.class))
                .thenReturn(pokemonListMockResponse);
        when(restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/1/", String.class))
                .thenReturn(pokemonMockResponse);

        CustomPokemonList pokemonList = pokemonService.getDetailedPokemonList(0, 1);
        assertNotNull(pokemonList);
        assertEquals(pokemonList.getResults().size(), 1);
        assertTrue(pokemonList.getResults().get(0).getName().contains("bulbasaur"));
        assertEquals(1, pokemonList.getResults().get(0).getId());
        assertEquals(69, pokemonList.getResults().get(0).getWeight());
    }

    @Test
    void testGetPokemonDetail() throws IOException {
        String pokemonSpecieMockResponse = TestUtils.readFileAsString(
                "src/test/resources/pokemonSpecieMockResponse.json"
        );
        String pokemonEvolutionChainMockResponse = TestUtils.readFileAsString(
                "src/test/resources/pokemonEvolutionChainMockResponse.json"
        );
        when(restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon-species/1", String.class))
                .thenReturn(pokemonSpecieMockResponse);
        when(restTemplate.getForObject("https://pokeapi.co/api/v2/evolution-chain/1/", String.class))
                .thenReturn(pokemonEvolutionChainMockResponse);

        CustomPokemonDetail pokemonDetail = pokemonService.getPokemonDetail(1);
        assertNotNull(pokemonDetail);
        assertNotNull(pokemonDetail.getDescription());
        assertNotNull(pokemonDetail.getEvolutionChain());
    }
}
