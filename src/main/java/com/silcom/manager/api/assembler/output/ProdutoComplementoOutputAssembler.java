package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.ProdutoComplementoOutputDTO;
import com.silcom.manager.domain.model.ProdutoComplemento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoComplementoOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ProdutoComplementoOutputDTO toDTO(ProdutoComplemento produtoComplemento) {
        return modelMapper.map(produtoComplemento, ProdutoComplementoOutputDTO.class);
    }

    public List<ProdutoComplementoOutputDTO> toColletionDTO(List<ProdutoComplemento> produtoComplementos) {
        return produtoComplementos.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
