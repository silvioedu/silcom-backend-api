package com.silcom.manager.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.ClienteLembrete;
import com.silcom.manager.domain.repository.ClienteLembreteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteLembreteService {
    
    private static final String ID_NOT_FOUND = "Cliente lembrete id %d não encontrado para o cliente id %d";

    @Autowired
    private ClienteLembreteRepository clienteLembreteRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private LembreteTipoService lembreteTipoService;

    public boolean existsByLembreteTipoId(Long lembreteTipoId) {
        return clienteLembreteRepository.existsByLembreteTipoId(lembreteTipoId);
    }

    public List<ClienteLembrete> findAll(final Long clienteId) {
        clienteService.findById(clienteId);
        return clienteLembreteRepository.findAllByClienteIdOrderByLembreteTipoNomeAsc(clienteId);
    }

    public ClienteLembrete findById(final Long clienteId, final Long id) {
        clienteService.findById(clienteId);

        return clienteLembreteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id, clienteId)
            ));
    }

    @Transactional
    public ClienteLembrete insert(final Long clienteId, final ClienteLembrete clienteLembrete) {
        ClienteLembrete clienteLembreteFormatado = createclienteLembrete(clienteLembrete, clienteId);
        return clienteLembreteRepository.save(clienteLembreteFormatado);
    }

    @Transactional
    public void delete(final Long clienteId, final Long id) {
        clienteLembreteRepository.delete(this.findById(clienteId, id));
    }

    @Transactional
    public ClienteLembrete update(Long clienteId, Long lembreteId, ClienteLembrete clienteLembrete) {
        ClienteLembrete clienteLembreteRecovered = this.findById(clienteId, lembreteId);
 
        clienteLembrete.setId(clienteLembreteRecovered.getId());
        clienteLembrete.setCliente(clienteLembreteRecovered.getCliente());
        clienteLembrete.setLembreteTipo(lembreteTipoService.findById(clienteLembrete.getLembreteTipo().getId()));
        clienteLembrete.setDataCriacao(clienteLembreteRecovered.getDataCriacao());
        return clienteLembreteRepository.save(clienteLembrete);
    }

    private ClienteLembrete createclienteLembrete(ClienteLembrete clienteLembrete, Long clienteId) {
        clienteLembrete.setCliente(clienteService.findById(clienteId));
        clienteLembrete.setLembreteTipo(lembreteTipoService.findById(clienteLembrete.getLembreteTipo().getId()));
        return clienteLembrete;
    }

    @Transactional
    public void deleteByClienteId(Long id) {
        clienteLembreteRepository.deleteByClienteId(id);
    }


}
