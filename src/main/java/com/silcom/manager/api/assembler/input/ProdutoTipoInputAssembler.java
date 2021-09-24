package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.ProdutoTipoInputDTO;
import com.silcom.manager.domain.model.ProdutoTipo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoTipoInputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ProdutoTipo toModel(ProdutoTipoInputDTO produtoTipo) {
        return modelMapper.map(produtoTipo, ProdutoTipo.class);
    }

}
