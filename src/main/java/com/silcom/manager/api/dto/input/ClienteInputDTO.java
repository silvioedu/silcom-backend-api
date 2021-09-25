package com.silcom.manager.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInputDTO {
    
    @NotBlank
    @Size(max=100)
    private String razaoSocial;

    @NotBlank
    @Size(max=100)
    private String nomeFantasia;

    @NotNull
    @Positive
    private Long ramoId;

    @NotBlank
    @Size(max=01)
    private String tipoPessoa;
 
    private String observacoes;
 
}
