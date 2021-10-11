package com.silcom.manager.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class EmailMessage {

    private String sender;

    private List<String> recipients;;

    private String subject;

    private String body;
    
}
