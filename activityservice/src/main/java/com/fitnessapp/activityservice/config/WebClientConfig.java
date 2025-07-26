package com.fitnessapp.activityservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // creating a webclient .builder, which is specialize web client for the user service.
    // using this we will be making calls to the user service
        @Bean
        @LoadBalanced
        public WebClient.Builder builder(){
            return WebClient.builder();
        }
        @Bean
        public WebClient userServiceWebClient(WebClient.Builder webClientBuilder){
            return webClientBuilder
                    .baseUrl("http://USER-SERVICE")
                    .build();
        }
}
