package org.example.managerapp.configuration;

import org.example.managerapp.client.RestClientTaskClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfiguration {

    @Bean
    public RestClientTaskClient restClientTaskClient(@Value("${newrav1k.services.catalogue.url:http://localhost:8081}") String baseUrl,
                                                     @Value("${newrav1k.services.catalogue.username:}") String catalogueUsername,
                                                     @Value("${newrav1k.services.catalogue.password:}") String cataloguePassword) {
        return new RestClientTaskClient(RestClient.builder()
                .baseUrl(baseUrl)
                .requestInterceptor(
                        new BasicAuthenticationInterceptor(catalogueUsername, cataloguePassword))
                .build());
    }

}