package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.DocumentoTipoOutputDTO;
import com.silcom.manager.domain.model.DocumentoTipo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentoTipoOutputAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public DocumentoTipoOutputDTO toDTO(DocumentoTipo documentoTipo) {
        return modelMapper.map(documentoTipo, DocumentoTipoOutputDTO.class);
    }

    public List<DocumentoTipoOutputDTO> toColletionDTO(List<DocumentoTipo> documentoTipos) {
        return documentoTipos.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
