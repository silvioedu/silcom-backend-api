package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.ProdutoComplementoInputDTO;
import com.silcom.manager.domain.model.ProdutoComplemento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoComplementoInputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ProdutoComplemento toModel(ProdutoComplementoInputDTO produtoComplemento) {
        return modelMapper.map(produtoComplemento, ProdutoComplemento.class);
    }

}
