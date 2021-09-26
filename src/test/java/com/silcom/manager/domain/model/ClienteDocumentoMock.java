package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.ClienteDocumentoInputDTO;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class ClienteDocumentoMock {
    
    public static ClienteDocumento getInstance() {
        ClienteDocumento clienteDocumento = new ClienteDocumento();
        clienteDocumento.setId(RandomUtils.nextLong(1, 24));
        clienteDocumento.setDocumentoTipo(DocumentoTipoMock.getRealInstance());
        clienteDocumento.setDocumento(RandomStringUtils.randomAlphanumeric(10));
        clienteDocumento.setObservacoes("Observação " + clienteDocumento.getId());
        clienteDocumento.setDataCriacao(OffsetDateTime.now());
        clienteDocumento.setDataAtualizacao(OffsetDateTime.now());
        return clienteDocumento;
    }

    public static ClienteDocumentoInputDTO getInputInstance() {
        ClienteDocumento clienteDocumento = getInstance();
        ClienteDocumentoInputDTO input = new ClienteDocumentoInputDTO();
        input.setTipoDocumentoId(clienteDocumento.getDocumentoTipo().getId());
        input.setDocumento(clienteDocumento.getDocumento());
        input.setObservacoes(clienteDocumento.getObservacoes());
        return input;
    }

}
