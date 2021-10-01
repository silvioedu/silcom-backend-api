package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.EnderecoTipoInputDTO;
import com.silcom.manager.domain.model.EnderecoTipo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoTipoInputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public EnderecoTipo toModel(EnderecoTipoInputDTO documentoTipo) {
        return modelMapper.map(documentoTipo, EnderecoTipo.class);
    }

}
