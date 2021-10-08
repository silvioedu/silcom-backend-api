package com.silcom.manager.api.dto.output;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteLembreteOutputDTO {
    
    private Long id;
    private String tipoLembreteNome;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataEvento;
    private String observacoes;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;

}
