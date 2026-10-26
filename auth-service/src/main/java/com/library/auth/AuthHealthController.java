package com.library.auth;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthHealthController {
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("service", "AUTH-SERVICE", "status", "UP");
    }
}
