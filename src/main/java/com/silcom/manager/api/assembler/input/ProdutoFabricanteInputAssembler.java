package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.ProdutoFabricanteInputDTO;
import com.silcom.manager.domain.model.ProdutoFabricante;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoFabricanteInputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ProdutoFabricante toModel(ProdutoFabricanteInputDTO produtoFabricante) {
        return modelMapper.map(produtoFabricante, ProdutoFabricante.class);
    }

}
