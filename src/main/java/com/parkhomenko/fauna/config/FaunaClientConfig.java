package com.parkhomenko.fauna.config;

import com.faunadb.client.FaunaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;

@Configuration
public class FaunaClientConfig {

    @Bean
    public FaunaClient faunaClient() throws MalformedURLException {
        return FaunaClient.builder()
                .withEndpoint("https://my-fauna-endpoint")
                .withSecret("put-your-key-secret-here")
                .build();
    }
}
