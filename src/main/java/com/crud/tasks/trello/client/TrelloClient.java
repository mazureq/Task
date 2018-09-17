package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TrelloClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UrlBuilder urlBuilder;

    public List<TrelloBoardDto> getTrelloBoards() {


        TrelloBoardDto[] boardresponse = restTemplate.getForObject(urlBuilder.urlBuild(), TrelloBoardDto[].class);
         if (boardresponse != null) {
             return Arrays.asList(boardresponse);
        }
        return new ArrayList<>();
    }
}
