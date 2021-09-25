package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.ClienteContatoInputDTO;
import com.silcom.manager.domain.model.ClienteContato;
import com.silcom.manager.domain.model.ContatoTipo;

import org.springframework.stereotype.Component;

@Component
public class ClienteContatoInputAssembler {
    
    public ClienteContato toModel(ClienteContatoInputDTO clienteContatoInputDTO) {
        ContatoTipo contatoTipo = new ContatoTipo();
        contatoTipo.setId(clienteContatoInputDTO.getTipoContatoId());

        ClienteContato clienteContato = new ClienteContato();
        clienteContato.setContatoTipo(contatoTipo);
        clienteContato.setContato(clienteContatoInputDTO.getContato());
        clienteContato.setObservacoes(clienteContatoInputDTO.getObservacoes());
        return clienteContato;
    }

}
