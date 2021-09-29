package com.silcom.manager.api.dto.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteVendaOutputDTO {
    
    private Long id;
    private String formaPagamentoTipoNome;
    private BigDecimal desconto;
    private BigDecimal agravo;
    private BigDecimal valorTotal;
    private boolean emitirNota;
    private String observacoes;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;

}
