package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.VendaOutputDTO;
import com.silcom.manager.domain.model.Venda;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendaOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public VendaOutputDTO toDTO(Venda venda) {
        return modelMapper.map(venda, VendaOutputDTO.class);
    }

    public List<VendaOutputDTO> toColletionDTO(List<Venda> vendas) {
        return vendas.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
