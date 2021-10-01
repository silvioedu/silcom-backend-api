package com.silcom.manager.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteEnderecoInputDTO {
    
    @NotNull
    @Positive
    private Long tipoEnderecoId;

    @NotBlank
    @Size(max=9)
    private String cep;

    @NotBlank
    @Size(max=100)
    private String logradouro;

    @NotBlank
    @Size(max=20)
    private String numero;

    @Size(max=100)
	private String complemento;

    @NotBlank
    @Size(max=60)
    private String bairro;

	@NotBlank
    @Size(max=60)
    private String cidade;

    @NotBlank
    @Size(max=2)
    private String estado;

    private String observacoes;

}

