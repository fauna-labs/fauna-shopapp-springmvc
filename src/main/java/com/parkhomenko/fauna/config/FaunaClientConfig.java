package com.parkhomenko.fauna.config;

import com.faunadb.client.FaunaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.util.Map;

@Configuration
public class FaunaClientConfig {

    @Value("${fauna.datasource.url}")
    private String url;

    @Value("${fauna.datasource.secret}")
    private String secret;

    @Bean
    public FaunaClient faunaClient() throws MalformedURLException {
        return FaunaClient.builder()
                .withEndpoint(url)
                .withSecret(secret)
                .withCustomHeaders(Map.of("X-Fauna-Source", "shopapp-springmvc"))
                .build();
    }
}
