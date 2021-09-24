package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.DocumentoTipoInputDTO;

import org.apache.commons.lang3.RandomUtils;

public class DocumentoTipoMock {
    
    public static DocumentoTipo getInstance() {
        DocumentoTipo documentoTipo = new DocumentoTipo();
        documentoTipo.setId(RandomUtils.nextLong(1, 24));
        documentoTipo.setNome("Nome DctTipo " + documentoTipo.getId());
        documentoTipo.setDataCriacao(OffsetDateTime.now());

        return documentoTipo;
    }

    public static DocumentoTipoInputDTO getInputInstance() {
        DocumentoTipo documentoTipo = getInstance();
        DocumentoTipoInputDTO input = new DocumentoTipoInputDTO();
        input.setNome(documentoTipo.getNome());
        return input;
    }

}
