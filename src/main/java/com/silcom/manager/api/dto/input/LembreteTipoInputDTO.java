package com.silcom.manager.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LembreteTipoInputDTO {
    
    @NotBlank
    @Size(max=20)
    private String nome;

    @NotNull
    private Integer intervalo;
    
}
