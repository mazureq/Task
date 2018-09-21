package com.crud.tasks.trello.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Configuration
public class UrlBuilder {

    @Value("${trello.app.username}")
    private String username;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Bean
    public URI urlBuild() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/member/" + username + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name, id")
                .queryParam("lists", "all")
                .build().encode().toUri();
        return url;
    }

}
