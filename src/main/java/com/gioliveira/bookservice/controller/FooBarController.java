package com.gioliveira.bookservice.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.var;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("book-service")
public class FooBarController {

    static AtomicInteger atomicInteger = new AtomicInteger(0);
    
    @GetMapping("/foo-bar")
    @Retry(name = "default", fallbackMethod = "fallbackMethod")
    public String fooBar() {
        
        log.info("Request to foobar is Received, Retry {}", atomicInteger.getAndIncrement());
        var response = new RestTemplate().getForEntity("http://localhost:8000/foo-bar", String.class);
        return response.getBody();
    }

    public String fallbackMethod(Exception ex) {
        log.error("Request to fallback {}", ex.getMessage());
        return "Return fallbackMethod foo-bar";        
    }
    
}
