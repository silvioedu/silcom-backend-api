package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.ContatoTipoInputDTO;

import org.apache.commons.lang3.RandomUtils;

public class ContatoTipoMock {
    
    public static ContatoTipo getInstance() {
        ContatoTipo contatoTipo = new ContatoTipo();
        contatoTipo.setId(RandomUtils.nextLong(1, 24));
        contatoTipo.setNome("Nome CttTipo " + contatoTipo.getId());
        contatoTipo.setDataCriacao(OffsetDateTime.now());

        return contatoTipo;
    }

    public static ContatoTipoInputDTO getInputInstance() {
        ContatoTipo contatoTipo = getInstance();
        ContatoTipoInputDTO input = new ContatoTipoInputDTO();
        input.setNome(contatoTipo.getNome());
        return input;
    }

}
