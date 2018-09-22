package com.crud.tasks.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AttachmentsByTypeObjectMapper {

    @JsonProperty("trello")
    private TrelloObjectMapper trelloObjectMapper;

}
