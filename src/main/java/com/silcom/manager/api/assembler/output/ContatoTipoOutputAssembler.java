package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.ContatoTipoOutputDTO;
import com.silcom.manager.domain.model.ContatoTipo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContatoTipoOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ContatoTipoOutputDTO toDTO(ContatoTipo contatoTipo) {
        return modelMapper.map(contatoTipo, ContatoTipoOutputDTO.class);
    }

    public List<ContatoTipoOutputDTO> toColletionDTO(List<ContatoTipo> contatoTipos) {
        return contatoTipos.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
