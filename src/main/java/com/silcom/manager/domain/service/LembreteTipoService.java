package com.silcom.manager.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.DuplicateKeyException;
import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.LembreteTipo;
import com.silcom.manager.domain.repository.LembreteTipoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LembreteTipoService {
    
    private static final String ALREADY_EXISTS = "Tipo de lembrete nome '%s' já existe";
    private static final String NOME_NOT_FOUND = "Não foram encontrados tipos de lembrete com o nome '%s'";
    private static final String ID_NOT_FOUND = "Tipo de lembrete id %d não encontrado";

    @Autowired
    private LembreteTipoRepository lembreteTipoRepository;

    public List<LembreteTipo> findAll() {
        return lembreteTipoRepository.findAllByOrderByNomeAsc();
    }

    public LembreteTipo findById(final Long id) {
        return lembreteTipoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id)
            ));
    }
    
    public List<LembreteTipo> findByNomeContaining(final String nome) {
        List<LembreteTipo> lembreteTipos = lembreteTipoRepository.findByNomeContainingIgnoreCase(nome);
        if (lembreteTipos.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format(NOME_NOT_FOUND, nome)
            ); 
        }
        return lembreteTipos;
    }

    @Transactional
    public LembreteTipo insert(final LembreteTipo lembreteTipo) {
        LembreteTipo lembreteTipoFormatado = lembreteTipo;

        if (lembreteTipoRepository.existsByNomeIgnoreCase(lembreteTipo.getNome())) {
            throw new DuplicateKeyException(
                String.format(ALREADY_EXISTS, lembreteTipo.getNome()));
        }
        return lembreteTipoRepository.save(lembreteTipoFormatado);
    }

    @Transactional
    public void delete(final Long id) {
        lembreteTipoRepository.delete(this.findById(id));
    }

    @Transactional
    public LembreteTipo update(final Long id, final LembreteTipo lembreteTipo) {
        LembreteTipo lembreteTipoFormatado = lembreteTipo;
        
        LembreteTipo lembreteTipoRecovered = this.findById(id);
        if ((!lembreteTipoRecovered.getNome().equals(lembreteTipoFormatado.getNome()) &&
            lembreteTipoRepository.existsByNomeIgnoreCase(lembreteTipo.getNome()))) {
                throw new DuplicateKeyException(
                    String.format(ALREADY_EXISTS, lembreteTipo.getNome()));   
        }
        lembreteTipoFormatado.setId(id);
        lembreteTipoFormatado.setDataCriacao(lembreteTipoRecovered.getDataCriacao());
        lembreteTipoFormatado.setDataAtualizacao(OffsetDateTime.now());
        return lembreteTipoRepository.save(lembreteTipoFormatado);
    }

}