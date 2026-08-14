package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@RestController
public class HelloController {

    @Autowired
    private SecretService secretService;

    @Value("${secreto.vars:SECRET_VALUE,USER,HOSTNAME}")
    private String secretoVars;

    @GetMapping("/hello")
    public String hello() {
        return "Hola Leonardo";
    }

    @GetMapping("/secreto")
    public ResponseEntity<Map<String, String>> secreto() {
        String value = secretService.getSecret();
        Map<String, String> resp = new HashMap<>();

        resp.put("secret", value != null ? value : "<no-set>");

        // determine source
        String source = (System.getenv("SECRET_VALUE") != null && !System.getenv("SECRET_VALUE").isBlank())
                ? "env:SECRET_VALUE" : (value != null ? "fallback" : "none");
        resp.put("source", source);

        // decide which vars to show: environment variable SECRETO_VARS takes precedence
        String vars = System.getenv().getOrDefault("SECRETO_VARS", secretoVars);
        Stream.of(vars.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .forEach(name -> resp.put(name, System.getenv().getOrDefault(name, "<no-set>")));

        // metadata
        resp.put("timestamp", Instant.now().toString());

        return ResponseEntity.ok(resp);
    }
}
