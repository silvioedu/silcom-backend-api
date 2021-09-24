package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.ProdutoCorInputDTO;
import com.silcom.manager.domain.model.ProdutoCor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoCorInputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ProdutoCor toModel(ProdutoCorInputDTO produtoCor) {
        return modelMapper.map(produtoCor, ProdutoCor.class);
    }

}
