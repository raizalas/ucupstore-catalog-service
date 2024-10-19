package com.ucupstore.catalogservice;

import com.ucupstore.catalogservice.config.GreetingProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final GreetingProperties greetingProperties;

    public HomeController(GreetingProperties greetingProperties) {
        this.greetingProperties = greetingProperties;
    }

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok(greetingProperties.getGreeting());
    }
}
