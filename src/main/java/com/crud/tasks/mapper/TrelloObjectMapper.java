package com.crud.tasks.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrelloObjectMapper {

    @JsonProperty("board")
    private int board;
    @JsonProperty("card")
    private int card;
}
