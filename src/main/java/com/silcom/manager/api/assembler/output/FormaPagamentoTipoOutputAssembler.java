package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.FormaPagamentoTipoOutputDTO;
import com.silcom.manager.domain.model.FormaPagamentoTipo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoTipoOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoTipoOutputDTO toDTO(FormaPagamentoTipo formaPagamentoTipo) {
        return modelMapper.map(formaPagamentoTipo, FormaPagamentoTipoOutputDTO.class);
    }

    public List<FormaPagamentoTipoOutputDTO> toColletionDTO(List<FormaPagamentoTipo> formaPagamentoTipos) {
        return formaPagamentoTipos.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
