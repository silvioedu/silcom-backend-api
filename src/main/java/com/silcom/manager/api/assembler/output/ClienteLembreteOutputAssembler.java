package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.ClienteLembreteOutputDTO;
import com.silcom.manager.domain.model.ClienteLembrete;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteLembreteOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ClienteLembreteOutputDTO toDTO(ClienteLembrete clienteLembrete) {
        return modelMapper.map(clienteLembrete, ClienteLembreteOutputDTO.class);
    }

    public List<ClienteLembreteOutputDTO> toColletionDTO(List<ClienteLembrete> clienteLembrete) {
        return clienteLembrete.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
