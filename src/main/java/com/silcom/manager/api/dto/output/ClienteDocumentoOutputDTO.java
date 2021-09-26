package com.silcom.manager.api.dto.output;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDocumentoOutputDTO {
    
    private Long id;
    private String tipoDocumentoNome;
    private String documento;
    private boolean isento;
    private String observacoes;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;

}
