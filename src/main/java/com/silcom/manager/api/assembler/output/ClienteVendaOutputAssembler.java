package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.ClienteVendaOutputDTO;
import com.silcom.manager.domain.model.ClienteVenda;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteVendaOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ClienteVendaOutputDTO toDTO(ClienteVenda venda) {
        return modelMapper.map(venda, ClienteVendaOutputDTO.class);
    }

    public List<ClienteVendaOutputDTO> toColletionDTO(List<ClienteVenda> vendas) {
        return vendas.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
