package com.fitnessapp.aiservice.aiservice;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class GeminiService {
    // This service interacts with the Gemini API to get answers to user questions.
// It uses WebClient to make HTTP requests and retrieves the response as a String.
// The service is configured with the Gemini API URL and key, which are injected from application properties.

    private final WebClient webClient;
    @Value("${gemini.api.url}")

    private String geminiApiUrl;
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getAnswer(String question){
        Map<String,Object> requestBody = Map.of("contents", new Object[]{
                Map.of("parts",new Object[]{
                   Map.of("text",question)
                })
        }
        );
        String response = webClient.post()
                .uri(geminiApiUrl + "?key=" + geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }

   }
