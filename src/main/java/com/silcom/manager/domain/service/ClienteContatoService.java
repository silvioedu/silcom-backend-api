package com.silcom.manager.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.ClienteContato;
import com.silcom.manager.domain.repository.ClienteContatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteContatoService {
    
    private static final String ID_NOT_FOUND = "Cliente contato id %d não encontrado para o cliente id %d";

    @Autowired
    private ClienteContatoRepository clienteContatoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ContatoTipoService contatoTipoService;

    public boolean existsByContatoTipoId(Long contatoTipoId) {
        return clienteContatoRepository.existsByContatoTipoId(contatoTipoId);
    }

    public List<ClienteContato> findAll(final Long clienteId) {
        clienteService.findById(clienteId);
        return clienteContatoRepository.findAllByClienteIdOrderByContatoTipoNomeAsc(clienteId);
    }

    public ClienteContato findById(final Long clienteId, final Long id) {
        clienteService.findById(clienteId);

        return clienteContatoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id, clienteId)
            ));
    }

    @Transactional
    public ClienteContato insert(final Long clienteId, final ClienteContato clienteContato) {
        ClienteContato clienteContatoFormatado = createclienteContato(clienteContato, clienteId);
        return clienteContatoRepository.save(clienteContatoFormatado);
    }

    @Transactional
    public void delete(final Long clienteId, final Long id) {
        clienteContatoRepository.delete(this.findById(clienteId, id));
    }

    @Transactional
    public ClienteContato update(Long clienteId, Long contatoId, ClienteContato clienteContato) {
        ClienteContato clienteContatoRecovered = this.findById(clienteId, contatoId);
 
        clienteContato.setId(clienteContatoRecovered.getId());
        clienteContato.setCliente(clienteContatoRecovered.getCliente());
        clienteContato.setContatoTipo(contatoTipoService.findById(clienteContato.getContatoTipo().getId()));
        clienteContato.setDataCriacao(clienteContatoRecovered.getDataCriacao());
        return clienteContatoRepository.save(clienteContato);
    }

    private ClienteContato createclienteContato(ClienteContato clienteContato, Long clienteId) {
        clienteContato.setCliente(clienteService.findById(clienteId));
        clienteContato.setContatoTipo(contatoTipoService.findById(clienteContato.getContatoTipo().getId()));
        return clienteContato;
    }

    public void deleteByClienteId(Long id) {
        clienteContatoRepository.deleteByClienteId(id);
    }


}
