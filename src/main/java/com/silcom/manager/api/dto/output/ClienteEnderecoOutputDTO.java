package com.silcom.manager.api.dto.output;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteEnderecoOutputDTO {
    
    private String tipoEnderecoNome;
    private Long id;
    private String cep;
    private String logradouro;
    private String numero;
	private String complemento;
    private String bairro;
	private String cidade;
    private String estado;
    private String observacoes;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;

}
