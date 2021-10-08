package com.silcom.manager.api.dto.input;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteLembreteInputDTO {
    
    @NotNull
    @Positive
    private Long tipoLembreteId;
    
    @NotNull
    private LocalDate dataEvento;
    
    private String observacoes;

}
