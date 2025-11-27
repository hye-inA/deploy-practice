package com.example.deployment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello from Self-Hosted CI/CD! (Java 21)\n";
    }

    @GetMapping("/health-custom")
    public String health() {
        return "Application is running!\n";
    }

    @GetMapping("/time")
    public String currentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "🕐 Current server time: " + now.format(formatter);
    }
}
