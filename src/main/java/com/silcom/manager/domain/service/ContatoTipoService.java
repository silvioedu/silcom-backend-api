package com.silcom.manager.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.DuplicateKeyException;
import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.ContatoTipo;
import com.silcom.manager.domain.repository.ContatoTipoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoTipoService {
    
    private static final String ALREADY_EXISTS = "Tipo de contato nome '%s' já existe";
    private static final String NOME_NOT_FOUND = "Não foram encontrados tipos de contato com o nome '%s'";
    private static final String ID_NOT_FOUND = "Tipo de contato id %d não encontrado";

    @Autowired
    private ContatoTipoRepository contatoTipoRepository;

    public List<ContatoTipo> findAll() {
        return contatoTipoRepository.findAllByOrderByNomeAsc();
    }

    public ContatoTipo findById(final Long id) {
        return contatoTipoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id)
            ));
    }
    
    public List<ContatoTipo> findByNomeContaining(final String nome) {
        List<ContatoTipo> contatoTipos = contatoTipoRepository.findByNomeContainingIgnoreCase(nome);
        if (contatoTipos.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format(NOME_NOT_FOUND, nome)
            ); 
        }
        return contatoTipos;
    }

    @Transactional
    public ContatoTipo insert(final ContatoTipo contatoTipo) {
        ContatoTipo contatoTipoFormatado = contatoTipo;

        if (contatoTipoRepository.existsByNomeIgnoreCase(contatoTipo.getNome())) {
            throw new DuplicateKeyException(
                String.format(ALREADY_EXISTS, contatoTipo.getNome()));
        }
        return contatoTipoRepository.save(contatoTipoFormatado);
    }

    @Transactional
    public void delete(final Long id) {
        // TODO: verificar se existe algum cliente utilizando, entao lança exceção
        contatoTipoRepository.delete(this.findById(id));
    }

    @Transactional
    public ContatoTipo update(final Long id, final ContatoTipo contatoTipo) {
        ContatoTipo contatoTipoFormatado = contatoTipo;
        
        ContatoTipo contatoTipoRecovered = this.findById(id);
        if ((!contatoTipoRecovered.getNome().equals(contatoTipoFormatado.getNome()) &&
            contatoTipoRepository.existsByNomeIgnoreCase(contatoTipo.getNome()))) {
                throw new DuplicateKeyException(
                    String.format(ALREADY_EXISTS, contatoTipo.getNome()));   
        }
        contatoTipoFormatado.setId(id);
        contatoTipoFormatado.setDataCriacao(contatoTipoRecovered.getDataCriacao());
        contatoTipoFormatado.setDataAtualizacao(OffsetDateTime.now());
        return contatoTipoRepository.save(contatoTipoFormatado);
    }

}