package com.example.deployment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello from Self-Hosted CI/CD! (Java 21)";
    }

    @GetMapping("/health-custom")
    public String health() {
        return "Application is running!";
    }
}
