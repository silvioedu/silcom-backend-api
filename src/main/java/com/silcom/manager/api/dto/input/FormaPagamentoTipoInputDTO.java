package com.silcom.manager.api.dto.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoTipoInputDTO {
    
    @NotBlank
    @Size(max=100)
    private String nome;

    @NotNull
    @PositiveOrZero
    private BigDecimal desconto;

    @NotNull
    @PositiveOrZero
    private BigDecimal agravo;
    
}
