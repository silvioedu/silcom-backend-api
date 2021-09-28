package com.silcom.manager.api.dto.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FormaPagamentoTipoOutputDTO {
    
    private Long id;
    private String nome;
    private BigDecimal desconto;
    private BigDecimal agravo;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;

}
