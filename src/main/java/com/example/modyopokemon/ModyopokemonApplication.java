package com.example.modyopokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ModyopokemonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModyopokemonApplication.class, args);
	}

}
