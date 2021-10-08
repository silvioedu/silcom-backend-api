package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.LembreteTipoInputDTO;
import com.silcom.manager.domain.model.LembreteTipo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LembreteTipoInputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public LembreteTipo toModel(LembreteTipoInputDTO lembreteTipo) {
        return modelMapper.map(lembreteTipo, LembreteTipo.class);
    }

}
