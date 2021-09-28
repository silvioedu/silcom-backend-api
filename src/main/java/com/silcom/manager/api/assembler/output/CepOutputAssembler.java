package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.CepOutputDTO;
import com.silcom.manager.domain.model.Cep;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CepOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public CepOutputDTO toDTO(Cep cep) {
        return modelMapper.map(cep, CepOutputDTO.class);
    }

    public List<CepOutputDTO> toColletionDTO(List<Cep> cep) {
        return cep.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
