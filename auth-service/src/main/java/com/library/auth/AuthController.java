package com.library.auth;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        if ("student".equals(request.username()) && "library123".equals(request.password())) {
            return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "username", request.username(),
                "token", "demo-token-library-123"
            ));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Map.of("message", "Invalid username or password"));
    }

    public record LoginRequest(String username, String password) {}
}
