package com.crud.tasks.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyList() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("id", "name", true));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("id", "name", true));

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("id", "name", trelloListsDto));

        List<TrelloBoard> mappedTrelloBoard = new ArrayList<>();
        mappedTrelloBoard.add(new TrelloBoard("id", "name", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtoList);
        when(trelloMapper.mapToBoards(trelloBoardDtoList)).thenReturn(mappedTrelloBoard);
        when(trelloMapper.mapToBoardDto(anyList())).thenReturn(new ArrayList());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoard)).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> theTrelloBoardsDto = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(theTrelloBoardsDto);
        assertEquals(0, theTrelloBoardsDto.size());
    }

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("id", "name", true));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("id", "name", true));

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("id", "name", trelloListsDto));

        List<TrelloBoard> mappedTrelloBoard = new ArrayList<>();
        mappedTrelloBoard.add(new TrelloBoard("id", "name", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtoList);
        when(trelloMapper.mapToBoards(trelloBoardDtoList)).thenReturn(mappedTrelloBoard);
        when(trelloMapper.mapToBoardDto(mappedTrelloBoard)).thenReturn(trelloBoardDtoList);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoard)).thenReturn(mappedTrelloBoard);
        //When
        List<TrelloBoardDto>theTrelloBoards = trelloFacade.fetchTrelloBoards();
        //Then
        assertNotNull(theTrelloBoards);
        assertEquals(1, theTrelloBoards.size());

        theTrelloBoards.forEach(trelloBoardDto -> {
            assertEquals("id", trelloBoardDto.getId());
            assertEquals("name", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("id", trelloListDto.getId());
                assertEquals("name", trelloListDto.getName());
                assertEquals(true, trelloListDto.isClosed());
            });
        });

    }

}
