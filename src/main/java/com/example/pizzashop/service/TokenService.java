package com.example.pizzashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor

public class TokenService {
    private static SecureRandom secureRandom = new SecureRandom();
    private static Base64.Encoder encoder = Base64.getUrlEncoder();

    public String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return encoder.encodeToString(randomBytes);
    }

}
