package br.edu.ifal.contracts.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final RestTemplate restTemplate;

    public MessageController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<String> getMessage() {
        String ipAddress = Objects.requireNonNull(restTemplate.getForObject("https://api.ipify.org/?format=json", IpResponse.class)).getIp();

        return ResponseEntity.ok("Your IP is " + ipAddress);
    }

    private static class IpResponse {
        private String ip;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }
}
