package com.silcom.manager.domain.service;

import com.silcom.manager.domain.repository.ClienteDocumentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteDocumentoService {
    
    @Autowired
    private ClienteDocumentoRepository clienteDocumentoRepository;

    public boolean existsByDocumentoTipoId(Long documentoTipoId) {
        return clienteDocumentoRepository.existsByDocumentoTipoId(documentoTipoId);
    }
}
