package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.ProdutoIdOutputDTO;
import com.silcom.manager.domain.model.Produto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoIdOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ProdutoIdOutputDTO toDTO(Produto produto) {
        return modelMapper.map(produto, ProdutoIdOutputDTO.class);
    }

    public List<ProdutoIdOutputDTO> toColletionDTO(List<Produto> produtos) {
        return produtos.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
