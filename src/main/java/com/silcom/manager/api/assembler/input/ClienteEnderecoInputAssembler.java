package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.ClienteEnderecoInputDTO;
import com.silcom.manager.domain.model.ClienteEndereco;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteEnderecoInputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ClienteEndereco toModel(ClienteEnderecoInputDTO clienteEnderecoInputDTO) {
        return modelMapper.map(clienteEnderecoInputDTO, ClienteEndereco.class);
    }

}
