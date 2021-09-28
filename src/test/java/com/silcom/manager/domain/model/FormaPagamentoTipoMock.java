package com.silcom.manager.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.FormaPagamentoTipoInputDTO;

import org.apache.commons.lang3.RandomUtils;

public class FormaPagamentoTipoMock {
    
    public static FormaPagamentoTipo getInstance() {
        FormaPagamentoTipo formaPagamentoTipo = new FormaPagamentoTipo();
        formaPagamentoTipo.setId(RandomUtils.nextLong(1, 24));
        formaPagamentoTipo.setNome("Nome Fp " + formaPagamentoTipo.getId());
        formaPagamentoTipo.setDesconto(BigDecimal.valueOf(0));
        formaPagamentoTipo.setAgravo(BigDecimal.valueOf(1));
        formaPagamentoTipo.setDataCriacao(OffsetDateTime.now());

        return formaPagamentoTipo;
    }

    public static FormaPagamentoTipoInputDTO getInputInstance() {
        FormaPagamentoTipo formaPagamentoTipo = getInstance();
        FormaPagamentoTipoInputDTO input = new FormaPagamentoTipoInputDTO();
        input.setNome(formaPagamentoTipo.getNome());
        input.setDesconto(formaPagamentoTipo.getDesconto());
        input.setAgravo(formaPagamentoTipo.getAgravo());
        return input;
    }

    public static FormaPagamentoTipo getRealInstance() {
        FormaPagamentoTipo formaPagamentoTipo = new FormaPagamentoTipo();
        formaPagamentoTipo.setId(1L);
        formaPagamentoTipo.setNome("À vista");
        return formaPagamentoTipo;
    }
}
