package com.silcom.manager.api.dto.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteVendaItemInputDTO {
    
    @NotNull
    @Positive
    private Long produtoId;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @NotNull
    @PositiveOrZero
    private BigDecimal valorUnitario;

}
