package com.silcom.manager.api.dto.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteVendaItemOutputDTO {
    
    private Long id;
    private String produtoCodigo;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;    

}
