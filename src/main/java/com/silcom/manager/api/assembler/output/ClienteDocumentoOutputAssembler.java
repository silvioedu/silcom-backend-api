package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.ClienteDocumentoOutputDTO;
import com.silcom.manager.domain.model.ClienteDocumento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteDocumentoOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ClienteDocumentoOutputDTO toDTO(ClienteDocumento clienteDocumento) {
        return modelMapper.map(clienteDocumento, ClienteDocumentoOutputDTO.class);
    }

    public List<ClienteDocumentoOutputDTO> toColletionDTO(List<ClienteDocumento> clienteDocumento) {
        return clienteDocumento.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
