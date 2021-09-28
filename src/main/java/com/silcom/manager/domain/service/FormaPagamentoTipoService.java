package com.silcom.manager.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.DuplicateKeyException;
import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.FormaPagamentoTipo;
import com.silcom.manager.domain.repository.FormaPagamentoTipoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormaPagamentoTipoService {
    
    private static final String ALREADY_EXISTS = "Tipo de forma de pagamento nome '%s' já existe";
    private static final String NOME_NOT_FOUND = "Não foram encontrados tipos de forma de pagamento com o nome '%s'";
    private static final String ID_NOT_FOUND = "Tipo de forma de pagamento id %d não encontrado";

    @Autowired
    private FormaPagamentoTipoRepository formaPagamentoTipoRepository;

    public List<FormaPagamentoTipo> findAll() {
        return formaPagamentoTipoRepository.findAllByOrderByNomeAsc();
    }

    public FormaPagamentoTipo findById(final Long id) {
        return formaPagamentoTipoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id)
            ));
    }
    
    public List<FormaPagamentoTipo> findByNomeContaining(final String nome) {
        List<FormaPagamentoTipo> formaPagamentoTipos = formaPagamentoTipoRepository.findByNomeContainingIgnoreCase(nome);
        if (formaPagamentoTipos.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format(NOME_NOT_FOUND, nome)
            ); 
        }
        return formaPagamentoTipos;
    }

    @Transactional
    public FormaPagamentoTipo insert(final FormaPagamentoTipo formaPagamentoTipo) {
        FormaPagamentoTipo formaPagamentoTipoFormatado = formaPagamentoTipo;

        if (formaPagamentoTipoRepository.existsByNomeIgnoreCase(formaPagamentoTipo.getNome())) {
            throw new DuplicateKeyException(
                String.format(ALREADY_EXISTS, formaPagamentoTipo.getNome()));
        }
        return formaPagamentoTipoRepository.save(formaPagamentoTipoFormatado);
    }

    @Transactional
    public void delete(final Long id) {
        // TODO: verificar se tem venda utilizando
        formaPagamentoTipoRepository.delete(this.findById(id));
    }

    @Transactional
    public FormaPagamentoTipo update(final Long id, final FormaPagamentoTipo formaPagamentoTipo) {
        FormaPagamentoTipo formaPagamentoTipoFormatado = formaPagamentoTipo;
        
        FormaPagamentoTipo formaPagamentoTipoRecovered = this.findById(id);
        if ((!formaPagamentoTipoRecovered.getNome().equals(formaPagamentoTipoFormatado.getNome()) &&
            formaPagamentoTipoRepository.existsByNomeIgnoreCase(formaPagamentoTipo.getNome()))) {
                throw new DuplicateKeyException(
                    String.format(ALREADY_EXISTS, formaPagamentoTipo.getNome()));   
        }
        formaPagamentoTipoFormatado.setId(id);
        formaPagamentoTipoFormatado.setDataCriacao(formaPagamentoTipoRecovered.getDataCriacao());
        formaPagamentoTipoFormatado.setDataAtualizacao(OffsetDateTime.now());
        return formaPagamentoTipoRepository.save(formaPagamentoTipoFormatado);
    }

}