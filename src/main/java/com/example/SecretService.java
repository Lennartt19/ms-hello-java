package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SecretService {

    @Value("${secreto.fallback:}")
    private String fallback;

    public String getSecret() {
        // Read from environment variable SECRET_VALUE first
        String env = System.getenv("SECRET_VALUE");
        if (env != null && !env.isBlank()) {
            return env;
        }
        // Then use fallback property if provided
        if (fallback != null && !fallback.isBlank()) {
            return fallback;
        }
        return null;
    }
}
