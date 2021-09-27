package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.ClienteEnderecoOutputDTO;
import com.silcom.manager.domain.model.ClienteEndereco;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteEnderecoOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ClienteEnderecoOutputDTO toDTO(ClienteEndereco clienteEndereco) {
        return modelMapper.map(clienteEndereco, ClienteEnderecoOutputDTO.class);
    }

    public List<ClienteEnderecoOutputDTO> toColletionDTO(List<ClienteEndereco> clienteEndereco) {
        return clienteEndereco.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
