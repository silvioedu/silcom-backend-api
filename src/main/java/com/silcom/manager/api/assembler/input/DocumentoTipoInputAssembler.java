package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.DocumentoTipoInputDTO;
import com.silcom.manager.domain.model.DocumentoTipo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentoTipoInputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public DocumentoTipo toModel(DocumentoTipoInputDTO documentoTipo) {
        return modelMapper.map(documentoTipo, DocumentoTipo.class);
    }

}
