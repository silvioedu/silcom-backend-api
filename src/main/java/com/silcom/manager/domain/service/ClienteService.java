package com.silcom.manager.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.DuplicateKeyException;
import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.Cliente;
import com.silcom.manager.domain.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    private static final String ALREADY_EXISTS = "Cliente razão social '%s' já existe";
    private static final String NOME_NOT_FOUND = "Não foram encontrados clientes com a razão social '%s'";
    private static final String ID_NOT_FOUND = "Cliente id %d não encontrado";

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RamoService ramoService;

    @Autowired
    private ClienteContatoService clienteContatoService;

    @Autowired
    private ClienteDocumentoService clienteDocumentoService;

    @Autowired
    private ClienteEnderecoService clienteEnderecoService;

    public List<Cliente> findAll() {
        return clienteRepository.findAllByOrderByRazaoSocialAsc();
    }

    public Cliente findById(final Long id) {
        return clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id)
            ));
    }
    
    public List<Cliente> findByRazaoSocialContaining(final String razaoSocial) {
        List<Cliente> clientes = clienteRepository.findByRazaoSocialContainingIgnoreCase(razaoSocial);
        if (clientes.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format(NOME_NOT_FOUND, razaoSocial)
            ); 
        }
        return clientes;
    }

    @Transactional
    public Cliente insert(final Cliente cliente) {
        Cliente clienteFormatado = createcliente(cliente);

        if (clienteRepository.existsByRazaoSocialIgnoreCase(cliente.getRazaoSocial())) {
            throw new DuplicateKeyException(
                String.format(ALREADY_EXISTS, cliente.getRazaoSocial()));
        }
        return clienteRepository.save(clienteFormatado);
    }

    @Transactional
    public void delete(final Long id) {
        // TODO: verificar se possui pedidos e impedir
        
        clienteContatoService.deleteByClienteId(id);
        clienteDocumentoService.deleteByClienteId(id);
        clienteEnderecoService.deleteByClienteId(id);
        clienteRepository.delete(this.findById(id));
    }

    @Transactional
    public Cliente update(final Long id, final Cliente cliente) {
        Cliente clienteFormatado = createcliente(cliente);
        
        Cliente clienteRecovered = this.findById(id);
        if ((!clienteRecovered.getRazaoSocial().equals(clienteFormatado.getRazaoSocial()) &&
            clienteRepository.existsByRazaoSocialIgnoreCase(cliente.getRazaoSocial()))) {
                throw new DuplicateKeyException(
                    String.format(ALREADY_EXISTS, cliente.getRazaoSocial()));   
        }
        clienteFormatado.setId(id);
        clienteFormatado.setDataCriacao(clienteRecovered.getDataCriacao());
        clienteFormatado.setDataAtualizacao(OffsetDateTime.now());
        return clienteRepository.save(clienteFormatado);
    }

    private Cliente createcliente(final Cliente cliente) {
        cliente.setRamo(this.ramoService.findById(cliente.getRamo().getId()));

        return cliente;
    }

}