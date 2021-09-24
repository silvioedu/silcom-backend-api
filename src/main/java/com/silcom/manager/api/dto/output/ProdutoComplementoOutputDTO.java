package com.silcom.manager.api.dto.output;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoComplementoOutputDTO {
    
    private Long id;
    private String nome;
    private String sigla;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;

}
