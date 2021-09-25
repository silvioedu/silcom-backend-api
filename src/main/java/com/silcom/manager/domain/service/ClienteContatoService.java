package com.silcom.manager.domain.service;

import com.silcom.manager.domain.repository.ClienteContatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteContatoService {
    
    @Autowired
    private ClienteContatoRepository clienteContatoRepository;

    public boolean existsByContatoTipoId(Long contatoTipoId) {
        return clienteContatoRepository.existsByContatoTipoId(contatoTipoId);
    }
}
