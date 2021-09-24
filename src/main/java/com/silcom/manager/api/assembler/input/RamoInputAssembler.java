package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.RamoInputDTO;
import com.silcom.manager.domain.model.Ramo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RamoInputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public Ramo toModel(RamoInputDTO ramo) {
        return modelMapper.map(ramo, Ramo.class);
    }

}
