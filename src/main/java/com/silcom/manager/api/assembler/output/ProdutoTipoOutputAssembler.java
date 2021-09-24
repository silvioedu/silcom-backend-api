package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.ProdutoTipoOutputDTO;
import com.silcom.manager.domain.model.ProdutoTipo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoTipoOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ProdutoTipoOutputDTO toDTO(ProdutoTipo produtoTipo) {
        return modelMapper.map(produtoTipo, ProdutoTipoOutputDTO.class);
    }

    public List<ProdutoTipoOutputDTO> toColletionDTO(List<ProdutoTipo> produtoTipos) {
        return produtoTipos.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
