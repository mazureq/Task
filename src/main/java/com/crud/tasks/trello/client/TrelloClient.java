package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.CreatedTrelloCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {

        return restTemplate.postForObject(urlBuilder.urlPost(trelloCardDto), null, CreatedTrelloCard.class);
    }
}
