package com.silcom.manager.api.dto.output.report;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class VendaItemReportDTO {
    
    private String codigo;
    private String descricao;
    private Integer tamanho;
    private Integer quantidade;
    private BigDecimal valorUnitario;

}
