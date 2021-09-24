package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.ProdutoOutputDTO;
import com.silcom.manager.domain.model.Produto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ProdutoOutputDTO toDTO(Produto produto) {
        return modelMapper.map(produto, ProdutoOutputDTO.class);
    }

    public List<ProdutoOutputDTO> toColletionDTO(List<Produto> produtos) {
        return produtos.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
