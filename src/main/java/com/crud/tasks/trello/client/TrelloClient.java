package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.CreatedTrelloCardDto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;

import static java.util.Optional.ofNullable;

@Component
public class TrelloClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UrlBuilder urlBuilder;

    public List<TrelloBoardDto> getTrelloBoards() {

        try {
            TrelloBoardDto[] boardresponse = restTemplate.getForObject(urlBuilder.urlBuild(), TrelloBoardDto[].class);
            return Arrays.asList(ofNullable(boardresponse).orElse(new TrelloBoardDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public CreatedTrelloCardDto createNewCard(TrelloCardDto trelloCardDto) {

        return restTemplate.postForObject(urlBuilder.urlPost(trelloCardDto), null, CreatedTrelloCardDto.class);

    }
}
