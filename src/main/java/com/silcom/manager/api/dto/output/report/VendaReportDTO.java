package com.silcom.manager.api.dto.output.report;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.silcom.manager.domain.model.VendaStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
@Setter
public class VendaReportDTO {
    
    private Long id;

    private OffsetDateTime dataCriacao;
    private String formaPagamento;
    private boolean emitirNota;
    private VendaStatus status;
    
    private String clienteRazaoSocial;
    private String documento;
    private String endereco;
    private String email;
    private String telefone;
    private String observacoes;

    private List<VendaItemReportDTO> itens;
    private BigDecimal valorTotal;

}
