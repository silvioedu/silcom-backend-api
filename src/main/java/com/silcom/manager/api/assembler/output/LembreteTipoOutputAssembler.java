package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.LembreteTipoOutputDTO;
import com.silcom.manager.domain.model.LembreteTipo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LembreteTipoOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public LembreteTipoOutputDTO toDTO(LembreteTipo lembreteTipo) {
        return modelMapper.map(lembreteTipo, LembreteTipoOutputDTO.class);
    }

    public List<LembreteTipoOutputDTO> toColletionDTO(List<LembreteTipo> lembreteTipos) {
        return lembreteTipos.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
