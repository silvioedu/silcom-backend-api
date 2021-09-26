package com.silcom.manager.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.ClienteDocumento;
import com.silcom.manager.domain.repository.ClienteDocumentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteDocumentoService {
    
    private static final String ID_NOT_FOUND = "Cliente documento id %d não encontrado para o cliente id %d";

    @Autowired
    private ClienteDocumentoRepository clienteDocumentoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DocumentoTipoService ddocumentoTipoService;

    public boolean existsByDocumentoTipoId(Long documentoTipoId) {
        return clienteDocumentoRepository.existsByDocumentoTipoId(documentoTipoId);
    }

    public List<ClienteDocumento> findAll(final Long clienteId) {
        clienteService.findById(clienteId);
        return clienteDocumentoRepository.findAllByClienteId(clienteId);
    }

    public ClienteDocumento findById(final Long clienteId, final Long id) {
        clienteService.findById(clienteId);

        return clienteDocumentoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id, clienteId)
            ));
    }

    @Transactional
    public ClienteDocumento insert(final Long clienteId, final ClienteDocumento clienteDocumento) {
        ClienteDocumento clienteDocumentoFormatado = createclienteDocumento(clienteDocumento, clienteId);
        return clienteDocumentoRepository.save(clienteDocumentoFormatado);
    }

    @Transactional
    public void delete(final Long clienteId, final Long id) {
        clienteDocumentoRepository.delete(this.findById(clienteId, id));
    }

    @Transactional
    public ClienteDocumento update(Long clienteId, Long ddocumentoId, ClienteDocumento clienteDocumento) {
        ClienteDocumento clienteDocumentoRecovered = this.findById(clienteId, ddocumentoId);
 
        clienteDocumento.setId(clienteDocumentoRecovered.getId());
        clienteDocumento.setCliente(clienteDocumentoRecovered.getCliente());
        clienteDocumento.setDocumentoTipo(ddocumentoTipoService.findById(clienteDocumento.getDocumentoTipo().getId()));
        clienteDocumento.setDataCriacao(clienteDocumentoRecovered.getDataCriacao());
        return clienteDocumentoRepository.save(clienteDocumento);
    }

    private ClienteDocumento createclienteDocumento(ClienteDocumento clienteDocumento, Long clienteId) {
        clienteDocumento.setCliente(clienteService.findById(clienteId));
        clienteDocumento.setDocumentoTipo(ddocumentoTipoService.findById(clienteDocumento.getDocumentoTipo().getId()));
        return clienteDocumento;
    }

}
