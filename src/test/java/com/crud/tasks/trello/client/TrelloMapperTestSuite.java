package com.crud.tasks.trello.client;


import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper mappers;

    @Test
    public void testMapToBoardAndMapToList() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        List<TrelloListDto> trelloListDtos = new ArrayList<>();

        TrelloListDto list1 = new TrelloListDto("1", "list1", true);

        trelloListDtos.add(list1);

        TrelloBoardDto board1 = new TrelloBoardDto("1", "Board1", trelloListDtos);
        TrelloBoardDto board2 = new TrelloBoardDto("2", "Board2", trelloListDtos);
        TrelloBoardDto board3 = new TrelloBoardDto("3", "Board3", trelloListDtos);

        trelloBoardDtos.add(board1);
        trelloBoardDtos.add(board2);
        trelloBoardDtos.add(board3);

        //When
        List<TrelloBoard> list = mappers.mapToBoards(trelloBoardDtos);
        List<TrelloBoardDto> listDtos = mappers.mapToBoardDto(list);

        //Then
        Assert.assertEquals("Board2", list.get(1).getName());
        Assert.assertEquals("Board3", listDtos.get(2).getName());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Name", "Description", "pos", "listId");
        //When
        TrelloCard trelloCard = mappers.mapToTrelloCard(trelloCardDto);
        //Then
        Assert.assertEquals("Name", trelloCard.getName());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Name", "Description", "pos", "listId");
        //When
        TrelloCardDto trelloCardDto = mappers.mapToCardDto(trelloCard);
        //Then
        Assert.assertEquals("Name", trelloCardDto.getName());
    }
}
