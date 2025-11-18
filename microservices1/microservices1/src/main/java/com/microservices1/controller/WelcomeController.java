package com.microservices1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/message")
public class WelcomeController {
    //http://localhost:8081/api/v1/message/welcome
    //API-http://localhost:5050/micro1/api/v1/message/welcome
    @GetMapping("/welcome")
    public String getMessage() {
        return "Hello Microservices!";
    }
}
