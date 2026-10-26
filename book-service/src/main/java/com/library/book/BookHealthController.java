package com.library.book;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookHealthController {
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("service", "BOOK-SERVICE", "status", "UP");
    }
}
