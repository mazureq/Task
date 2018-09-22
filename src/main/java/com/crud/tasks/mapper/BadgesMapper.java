package com.crud.tasks.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BadgesMapper {

    private int votes;
    private AttachmentsByTypeObjectMapper attachmentsByTypeObjectMapper;

}
