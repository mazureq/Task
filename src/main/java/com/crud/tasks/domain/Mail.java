package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;



@Getter
@AllArgsConstructor
public class Mail {
    private String mailto;
    private String subject;
    private String message;
    private String toCc;

    public Mail(String mailto, String subject, String message) {
        this.mailto = mailto;
        this.subject = subject;
        this.message = message;
    }
}
