package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.EnderecoTipoOutputDTO;
import com.silcom.manager.domain.model.EnderecoTipo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoTipoOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public EnderecoTipoOutputDTO toDTO(EnderecoTipo documentoTipo) {
        return modelMapper.map(documentoTipo, EnderecoTipoOutputDTO.class);
    }

    public List<EnderecoTipoOutputDTO> toColletionDTO(List<EnderecoTipo> documentoTipos) {
        return documentoTipos.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
