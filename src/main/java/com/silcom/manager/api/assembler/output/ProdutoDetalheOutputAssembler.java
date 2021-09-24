package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.ProdutoDetalheOutputDTO;
import com.silcom.manager.domain.model.ProdutoDetalhe;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDetalheOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ProdutoDetalheOutputDTO toDTO(ProdutoDetalhe produtoDetalhe) {
        return modelMapper.map(produtoDetalhe, ProdutoDetalheOutputDTO.class);
    }

    public List<ProdutoDetalheOutputDTO> toColletionDTO(List<ProdutoDetalhe> produtoDetalhes) {
        return produtoDetalhes.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
