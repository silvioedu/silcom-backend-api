package com.silcom.manager.domain.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.ClienteLembreteInputDTO;

import org.apache.commons.lang3.RandomUtils;

public class ClienteLembreteMock {
    
    public static ClienteLembrete getInstance() {
        ClienteLembrete clienteLembrete = new ClienteLembrete();
        clienteLembrete.setId(RandomUtils.nextLong(1, 24));
        clienteLembrete.setLembreteTipo(LembreteTipoMock.getRealInstance());
        clienteLembrete.setDataEvento(LocalDate.now());
        clienteLembrete.setObservacoes("Observação " + clienteLembrete.getId());
        clienteLembrete.setDataCriacao(OffsetDateTime.now());
        clienteLembrete.setDataAtualizacao(OffsetDateTime.now());
        return clienteLembrete;
    }

    public static ClienteLembreteInputDTO getInputInstance() {
        ClienteLembrete clienteLembrete = getInstance();
        ClienteLembreteInputDTO input = new ClienteLembreteInputDTO();
        input.setTipoLembreteId(clienteLembrete.getLembreteTipo().getId());
        input.setDataEvento(clienteLembrete.getDataEvento());
        input.setObservacoes(clienteLembrete.getObservacoes());
        return input;
    }

}
