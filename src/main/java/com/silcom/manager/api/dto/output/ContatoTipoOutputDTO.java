package com.silcom.manager.api.dto.output;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContatoTipoOutputDTO {
    
    private Long id;
    private String nome;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;

}
