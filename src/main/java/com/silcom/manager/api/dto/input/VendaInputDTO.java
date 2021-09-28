package com.silcom.manager.api.dto.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaInputDTO {
    
    @NotNull
    @Positive
    private Long formaPagamentoTipoId;
    
    @NotNull
    @PositiveOrZero
    private BigDecimal desconto;

    @NotNull
    @PositiveOrZero
    private BigDecimal agravo;

    @NotNull
    @PositiveOrZero
    private BigDecimal valorTotal;

    @NotNull
    private boolean emitirNota;

    private String observacoes;

}
