package com.silcom.manager.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.DuplicateKeyException;
import com.silcom.manager.domain.exception.ResourceInUseException;
import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.EnderecoTipo;
import com.silcom.manager.domain.repository.EnderecoTipoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoTipoService {
    
    private static final String ID_IN_USE = "Tipo de endereco id %d em uso";
    private static final String ALREADY_EXISTS = "Tipo de endereco nome '%s' já existe";
    private static final String NOME_NOT_FOUND = "Não foram encontrados tipos de endereco com o nome '%s'";
    private static final String ID_NOT_FOUND = "Tipo de endereco id %d não encontrado";

    @Autowired
    private EnderecoTipoRepository enderecoTipoRepository;

    @Autowired
    private ClienteEnderecoService clienteEnderecoService;

    public List<EnderecoTipo> findAll() {
        return enderecoTipoRepository.findAllByOrderByNomeAsc();
    }

    public EnderecoTipo findById(final Long id) {
        return enderecoTipoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id)
            ));
    }
    
    public List<EnderecoTipo> findByNomeContaining(final String nome) {
        List<EnderecoTipo> enderecoTipos = enderecoTipoRepository.findByNomeContainingIgnoreCase(nome);
        if (enderecoTipos.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format(NOME_NOT_FOUND, nome)
            ); 
        }
        return enderecoTipos;
    }

    @Transactional
    public EnderecoTipo insert(final EnderecoTipo enderecoTipo) {
        EnderecoTipo enderecoTipoFormatado = enderecoTipo;

        if (enderecoTipoRepository.existsByNomeIgnoreCase(enderecoTipo.getNome())) {
            throw new DuplicateKeyException(
                String.format(ALREADY_EXISTS, enderecoTipo.getNome()));
        }
        return enderecoTipoRepository.save(enderecoTipoFormatado);
    }

    @Transactional
    public void delete(final Long id) {
        if (clienteEnderecoService.existsByEnderecoTipoId(id)) {
            throw new ResourceInUseException(
                String.format(ID_IN_USE, id));
        }
        enderecoTipoRepository.delete(this.findById(id));
    }

    @Transactional
    public EnderecoTipo update(final Long id, final EnderecoTipo enderecoTipo) {
        EnderecoTipo enderecoTipoFormatado = enderecoTipo;
        
        EnderecoTipo enderecoTipoRecovered = this.findById(id);
        if ((!enderecoTipoRecovered.getNome().equals(enderecoTipoFormatado.getNome()) &&
            enderecoTipoRepository.existsByNomeIgnoreCase(enderecoTipo.getNome()))) {
                throw new DuplicateKeyException(
                    String.format(ALREADY_EXISTS, enderecoTipo.getNome()));   
        }
        enderecoTipoFormatado.setId(id);
        enderecoTipoFormatado.setDataCriacao(enderecoTipoRecovered.getDataCriacao());
        enderecoTipoFormatado.setDataAtualizacao(OffsetDateTime.now());
        return enderecoTipoRepository.save(enderecoTipoFormatado);
    }

}