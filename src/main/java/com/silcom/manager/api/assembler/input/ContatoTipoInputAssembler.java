package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.ContatoTipoInputDTO;
import com.silcom.manager.domain.model.ContatoTipo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContatoTipoInputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ContatoTipo toModel(ContatoTipoInputDTO contatoTipo) {
        return modelMapper.map(contatoTipo, ContatoTipo.class);
    }

}
