package com.silcom.manager.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.ClienteEndereco;
import com.silcom.manager.domain.repository.ClienteEnderecoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteEnderecoService {
    
    private static final String ID_NOT_FOUND = "Cliente endereço id %d não encontrado para o cliente id %d";

    @Autowired
    private ClienteEnderecoRepository clienteEnderecoRepository;

    @Autowired
    private ClienteService clienteService;

    public boolean existsByEnderecoTipoId(Long enderecoTipoId) {
        return clienteEnderecoRepository.existsByEnderecoTipoId(enderecoTipoId);
    }

    public List<ClienteEndereco> findAll(final Long clienteId) {
        clienteService.findById(clienteId);
        return clienteEnderecoRepository.findAllByClienteId(clienteId);
    }

    public ClienteEndereco findById(final Long clienteId, final Long id) {
        clienteService.findById(clienteId);

        return clienteEnderecoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id, clienteId)
            ));
    }

    @Transactional
    public ClienteEndereco insert(final Long clienteId, final ClienteEndereco clienteEndereco) {
        ClienteEndereco clienteEnderecoFormatado = createclienteEndereco(clienteEndereco, clienteId);
        clienteEnderecoFormatado.format();

        return clienteEnderecoRepository.save(clienteEnderecoFormatado);
    }

    @Transactional
    public void delete(final Long clienteId, final Long id) {
        clienteEnderecoRepository.delete(this.findById(clienteId, id));
    }

    @Transactional
    public ClienteEndereco update(Long clienteId, Long contatoId, ClienteEndereco clienteEndereco) {
        ClienteEndereco clienteEnderecoRecovered = this.findById(clienteId, contatoId);
        clienteEndereco.format();
 
        clienteEndereco.setId(clienteEnderecoRecovered.getId());
        clienteEndereco.setCliente(clienteEnderecoRecovered.getCliente());
        clienteEndereco.setDataCriacao(clienteEnderecoRecovered.getDataCriacao());
        return clienteEnderecoRepository.save(clienteEndereco);
    }

    private ClienteEndereco createclienteEndereco(ClienteEndereco clienteEndereco, Long clienteId) {
        clienteEndereco.setCliente(clienteService.findById(clienteId));
        return clienteEndereco;
    }

    @Transactional
    public void deleteByClienteId(Long id) {
        clienteEnderecoRepository.deleteByClienteId(id);
    }


}
