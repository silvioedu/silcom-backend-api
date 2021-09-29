package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.ClienteVendaItemOutputDTO;
import com.silcom.manager.domain.model.ClienteVendaItem;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteVendaItemOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ClienteVendaItemOutputDTO toDTO(ClienteVendaItem vendaItem) {
        return modelMapper.map(vendaItem, ClienteVendaItemOutputDTO.class);
    }

    public List<ClienteVendaItemOutputDTO> toColletionDTO(List<ClienteVendaItem> vendaItems) {
        return vendaItems.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
