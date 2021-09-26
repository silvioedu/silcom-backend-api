package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.ClienteDocumentoInputDTO;
import com.silcom.manager.domain.model.ClienteDocumento;
import com.silcom.manager.domain.model.DocumentoTipo;

import org.springframework.stereotype.Component;

@Component
public class ClienteDocumentoInputAssembler {
    
    public ClienteDocumento toModel(ClienteDocumentoInputDTO clienteDocumentoInputDTO) {
        DocumentoTipo documentoTipo = new DocumentoTipo();
        documentoTipo.setId(clienteDocumentoInputDTO.getTipoDocumentoId());

        ClienteDocumento clienteDocumento = new ClienteDocumento();
        clienteDocumento.setDocumentoTipo(documentoTipo);
        clienteDocumento.setDocumento(clienteDocumentoInputDTO.getDocumento());
        clienteDocumento.setIsento(clienteDocumentoInputDTO.isIsento()); 
        clienteDocumento.setObservacoes(clienteDocumentoInputDTO.getObservacoes());
        return clienteDocumento;
    }

}
