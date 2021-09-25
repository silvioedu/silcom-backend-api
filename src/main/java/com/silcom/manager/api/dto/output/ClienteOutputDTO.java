package com.silcom.manager.api.dto.output;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteOutputDTO {
    
    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    private String ramoNome;
    private String tipoPessoa;
    private String observacoes;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;

}
