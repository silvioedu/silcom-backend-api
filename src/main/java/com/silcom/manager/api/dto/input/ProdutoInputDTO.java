package com.silcom.manager.api.dto.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProdutoInputDTO {
    
    @NotNull
    @Positive
    private Long tipoId;

    @NotNull
    @Positive
    private Long corId;

    @NotNull
    @Positive
    private Long detalheId;

    @NotNull
    @Positive
    private Long complementoId;

    @NotNull
    @Positive
    private Long fabricanteId;

    @Size(max=07)
    private String folder;

    @NotNull
    @Positive
    private BigDecimal preco;
}
