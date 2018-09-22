package com.crud.tasks.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BadgesMapper {

    @JsonProperty("votes")
    private int votes;
    @JsonProperty("attachmentsByType")
    private AttachmentsByTypeObjectMapper attachments;

}
