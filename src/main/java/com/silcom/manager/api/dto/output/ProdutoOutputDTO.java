package com.silcom.manager.api.dto.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoOutputDTO {
    
    private Long id;
    private String tipoNome;
    private String corNome;
    private String detalheNome;
    private String complementoNome;
    private String fabricanteNome;
    private String codigo;
    private String folder;
    private BigDecimal preco;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;

}
