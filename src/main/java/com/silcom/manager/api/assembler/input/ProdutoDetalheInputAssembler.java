package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.ProdutoDetalheInputDTO;
import com.silcom.manager.domain.model.ProdutoDetalhe;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDetalheInputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ProdutoDetalhe toModel(ProdutoDetalheInputDTO produtoDetalhe) {
        return modelMapper.map(produtoDetalhe, ProdutoDetalhe.class);
    }

}
