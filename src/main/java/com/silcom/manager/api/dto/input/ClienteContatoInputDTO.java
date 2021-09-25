package com.silcom.manager.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteContatoInputDTO {
    
    @NotNull
    @Positive
    private Long tipoContatoId;
    
    @NotBlank
    private String contato;
    
    private String observacoes;

}
