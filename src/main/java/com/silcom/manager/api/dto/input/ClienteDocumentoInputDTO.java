package com.silcom.manager.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDocumentoInputDTO {
    
    @NotNull
    @Positive
    private Long tipoDocumentoId;
    
    @NotBlank
    private String documento;
    
    @NotNull
    private boolean isento;

    private String observacoes;

}
