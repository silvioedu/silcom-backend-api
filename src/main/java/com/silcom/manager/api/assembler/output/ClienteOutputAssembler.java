package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.ClienteOutputDTO;
import com.silcom.manager.domain.model.Cliente;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ClienteOutputDTO toDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteOutputDTO.class);
    }

    public List<ClienteOutputDTO> toColletionDTO(List<Cliente> cliente) {
        return cliente.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
