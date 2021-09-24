package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.ProdutoCorOutputDTO;
import com.silcom.manager.domain.model.ProdutoCor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoCorOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ProdutoCorOutputDTO toDTO(ProdutoCor produtoCor) {
        return modelMapper.map(produtoCor, ProdutoCorOutputDTO.class);
    }

    public List<ProdutoCorOutputDTO> toColletionDTO(List<ProdutoCor> produtoCors) {
        return produtoCors.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
