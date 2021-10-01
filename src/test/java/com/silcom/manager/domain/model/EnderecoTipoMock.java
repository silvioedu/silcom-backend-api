package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.EnderecoTipoInputDTO;

import org.apache.commons.lang3.RandomUtils;

public class EnderecoTipoMock {
    
    public static EnderecoTipo getInstance() {
        EnderecoTipo enderecoTipo = new EnderecoTipo();
        enderecoTipo.setId(RandomUtils.nextLong(1, 24));
        enderecoTipo.setNome("Nome Edrc " + enderecoTipo.getId());
        enderecoTipo.setDataCriacao(OffsetDateTime.now());

        return enderecoTipo;
    }

    public static EnderecoTipoInputDTO getInputInstance() {
        EnderecoTipo enderecoTipo = getInstance();
        EnderecoTipoInputDTO input = new EnderecoTipoInputDTO();
        input.setNome(enderecoTipo.getNome());
        return input;
    }

    public static EnderecoTipo getRealInstance() {
        EnderecoTipo enderecoTipo = new EnderecoTipo();
        enderecoTipo.setId(1L);
        enderecoTipo.setNome("Comercial");
        return enderecoTipo;
    }


}
