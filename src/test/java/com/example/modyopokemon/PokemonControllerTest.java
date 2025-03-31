package com.example.modyopokemon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PokemonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testBasicAuthenticationFail() throws Exception {
        String username = "modyoFake";
        String password = "98765";
        String credentials = username + ":" + password;
        String base64Credentials = java.util.Base64.getEncoder().encodeToString(credentials.getBytes());
        mockMvc.perform(get("/pokemon")
                        .param("offset", "0").param("limit", "5")
                        .header("Authorization", "Basic " + base64Credentials)
                ).andExpect(status().isUnauthorized());
    }

    @Test
    void testBasicAuthenticationSuccess() throws Exception {
        String username = "modyo";
        String password = "123456";
        String credentials = username + ":" + password;
        String base64Credentials = java.util.Base64.getEncoder().encodeToString(credentials.getBytes());
        mockMvc.perform(get("/pokemon")
                .param("offset", "0").param("limit", "5")
                .header("Authorization", "Basic " + base64Credentials)
        ).andExpect(status().is2xxSuccessful());
    }

    @Test
    void testGetPokemonList() throws Exception {
        String username = "modyo";
        String password = "123456";
        String credentials = username + ":" + password;
        String base64Credentials = java.util.Base64.getEncoder().encodeToString(credentials.getBytes());
        mockMvc.perform(get("/pokemon")
                .param("offset", "0").param("limit", "5")
                .header("Authorization", "Basic " + base64Credentials))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("\"name\":\"bulbasaur\"")));
    }

    @Test
    void testGetPokemonDetail() throws Exception {
        String username = "modyo";
        String password = "123456";
        String credentials = username + ":" + password;
        String base64Credentials = java.util.Base64.getEncoder().encodeToString(credentials.getBytes());
        mockMvc.perform(get("/pokemon-species/{specieId}", "1")
                        .header("Authorization", "Basic " + base64Credentials))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString(
                        "\"evolutionChain\":{\"name\":\"bulbasaur\",\"evolvesTo\":[{\"name\":\"ivysaur\""
                )));
    }
}
