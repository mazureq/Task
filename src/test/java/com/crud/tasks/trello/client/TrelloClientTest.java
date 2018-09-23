package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.BadgesMapper;
import com.crud.tasks.mapper.CreatedTrelloCard;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private UrlBuilder urlBuilder;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @Before
    public void init() {
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
    }

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        //Given
        URI uri = new URI("http://test.com/members/pawelmazurkiewicz3/boards?key=test&token=test&fields=name,id&lists=all");
        when(urlBuilder.urlBuild()).thenReturn(uri);

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    public void shouldCtreateCard() throws URISyntaxException {
        //Given
        URI uri = new URI("Dupa");
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "top", "test_id");
        when(urlBuilder.urlPost(trelloCardDto)).thenReturn(uri);
        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard("1", new BadgesMapper(), "name", "shortUrl");
        when(restTemplate.postForObject(uri, null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);
        //When
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);
        //Then
        assertEquals("1", newCard.getId());
        assertEquals("name", newCard.getName());
        assertEquals("shortUrl", newCard.getShortUrl());
    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {
        //Given
        URI uri = new URI("xxx");
        when(urlBuilder.urlBuild()).thenReturn(uri);
        when(restTemplate.getForObject(uri, TrelloBoardDto.class)).thenReturn(null);

        //When
        List<TrelloBoardDto> theList = trelloClient.getTrelloBoards();

        //Then
        assertEquals(0, theList.size());

    }
}