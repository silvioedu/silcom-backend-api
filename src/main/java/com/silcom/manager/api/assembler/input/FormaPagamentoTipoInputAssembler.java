package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.FormaPagamentoTipoInputDTO;
import com.silcom.manager.domain.model.FormaPagamentoTipo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoTipoInputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoTipo toModel(FormaPagamentoTipoInputDTO formaPagamentoTipo) {
        return modelMapper.map(formaPagamentoTipo, FormaPagamentoTipo.class);
    }

}
