package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.ClienteContatoInputDTO;

import org.apache.commons.lang3.RandomUtils;

public class ClienteContatoMock {
    
    public static ClienteContato getInstance() {
        ClienteContato clienteContato = new ClienteContato();
        clienteContato.setId(RandomUtils.nextLong(1, 24));
        clienteContato.setContatoTipo(ContatoTipoMock.getRealInstance());
        clienteContato.setContato("33112233");
        clienteContato.setObservacoes("Observação " + clienteContato.getId());
        clienteContato.setDataCriacao(OffsetDateTime.now());
        clienteContato.setDataAtualizacao(OffsetDateTime.now());
        return clienteContato;
    }

    public static ClienteContatoInputDTO getInputInstance() {
        ClienteContato clienteContato = getInstance();
        ClienteContatoInputDTO input = new ClienteContatoInputDTO();
        input.setTipoContatoId(clienteContato.getContatoTipo().getId());
        input.setContato(clienteContato.getContato());
        input.setObservacoes(clienteContato.getObservacoes());
        return input;
    }

}
