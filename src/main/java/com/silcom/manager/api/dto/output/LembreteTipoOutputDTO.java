package com.silcom.manager.api.dto.output;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LembreteTipoOutputDTO {
    
    private Long id;
    private String nome;
    private Integer intervalo;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;

}
