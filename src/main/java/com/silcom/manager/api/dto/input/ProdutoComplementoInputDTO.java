package com.silcom.manager.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoComplementoInputDTO {
    
    @NotBlank
    @Size(max=30)
    private String nome;

    @NotBlank
    @Size(max=01)
    private String sigla;
}
