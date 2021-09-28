package com.silcom.manager.api.dto.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaOutputDTO {
    
    private Long id;
    private Long formaPagamentoTipoId;
    private BigDecimal desconto;
    private BigDecimal agravo;
    private BigDecimal valorTotal;
    private boolean emitirNota;
    private String observacoes;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;

}
