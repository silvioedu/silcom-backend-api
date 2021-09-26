package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.ClienteContatoOutputDTO;
import com.silcom.manager.domain.model.ClienteContato;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteContatoOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ClienteContatoOutputDTO toDTO(ClienteContato clienteContato) {
        return modelMapper.map(clienteContato, ClienteContatoOutputDTO.class);
    }

    public List<ClienteContatoOutputDTO> toColletionDTO(List<ClienteContato> clienteContato) {
        return clienteContato.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
