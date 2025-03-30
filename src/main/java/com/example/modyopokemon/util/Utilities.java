package com.example.modyopokemon.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Utilities {
    public static String getCurrentBaseUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
    }
}
