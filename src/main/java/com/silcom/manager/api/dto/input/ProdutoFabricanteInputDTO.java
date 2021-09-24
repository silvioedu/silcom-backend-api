package com.silcom.manager.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoFabricanteInputDTO {
    
    @NotBlank
    @Size(max=20)
    private String nome;

    @NotBlank
    @Size(max=02)
    private String sigla;
}
