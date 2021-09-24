package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.ProdutoFabricanteOutputDTO;
import com.silcom.manager.domain.model.ProdutoFabricante;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoFabricanteOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ProdutoFabricanteOutputDTO toDTO(ProdutoFabricante produtoFabricante) {
        return modelMapper.map(produtoFabricante, ProdutoFabricanteOutputDTO.class);
    }

    public List<ProdutoFabricanteOutputDTO> toColletionDTO(List<ProdutoFabricante> produtoFabricantes) {
        return produtoFabricantes.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
