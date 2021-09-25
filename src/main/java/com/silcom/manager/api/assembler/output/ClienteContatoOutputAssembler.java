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

    public ClienteContatoOutputDTO toDTO(ClienteContato cliente) {
        return modelMapper.map(cliente, ClienteContatoOutputDTO.class);
    }

    public List<ClienteContatoOutputDTO> toColletionDTO(List<ClienteContato> cliente) {
        return cliente.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
