package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.ClienteLembreteInputDTO;
import com.silcom.manager.domain.model.ClienteLembrete;
import com.silcom.manager.domain.model.LembreteTipo;

import org.springframework.stereotype.Component;

@Component
public class ClienteLembreteInputAssembler {
    
    public ClienteLembrete toModel(ClienteLembreteInputDTO clienteLembreteInputDTO) {
        LembreteTipo lembreteTipo = new LembreteTipo();
        lembreteTipo.setId(clienteLembreteInputDTO.getTipoLembreteId());

        ClienteLembrete clienteLembrete = new ClienteLembrete();
        clienteLembrete.setLembreteTipo(lembreteTipo);
        clienteLembrete.setDataEvento(clienteLembreteInputDTO.getDataEvento());
        clienteLembrete.setObservacoes(clienteLembreteInputDTO.getObservacoes());
        return clienteLembrete;
    }

}
