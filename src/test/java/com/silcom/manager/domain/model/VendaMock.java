package com.silcom.manager.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.VendaInputDTO;

import org.apache.commons.lang3.RandomUtils;

public class VendaMock {
    
    public static Venda getInstance() {
        Venda venda = new Venda();
        venda.setId(RandomUtils.nextLong(1, 24));
        venda.setFormaPagamentoTipo(FormaPagamentoTipoMock.getRealInstance());
        venda.setDesconto(BigDecimal.valueOf(0));
        venda.setAgravo(BigDecimal.valueOf(1));
        venda.setValorTotal(BigDecimal.valueOf(2));
        venda.setEmitirNota(false);
        venda.setObservacoes("Observação " + venda.getId());
        venda.setDataCriacao(OffsetDateTime.now());
        venda.setDataAtualizacao(OffsetDateTime.now());
        return venda;
    }

    public static VendaInputDTO getInputInstance() {
        Venda venda = getInstance();
        VendaInputDTO input = new VendaInputDTO();
        input.setFormaPagamentoTipoId(venda.getFormaPagamentoTipo().getId());;
        input.setDesconto(venda.getDesconto());
        input.setAgravo(venda.getAgravo());
        input.setValorTotal(venda.getValorTotal());
        input.setEmitirNota(venda.isEmitirNota());
        input.setObservacoes(venda.getObservacoes());
        return input;
    }

}
