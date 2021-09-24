package com.silcom.manager.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.DuplicateKeyException;
import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.Ramo;
import com.silcom.manager.domain.repository.RamoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RamoService {
    
    private static final String ALREADY_EXISTS = "Ramo nome '%s' já existe";
    private static final String NOME_NOT_FOUND = "Não foram encontrados ramos com o nome '%s'";
    private static final String ID_NOT_FOUND = "Ramo id %d não encontrado";

    @Autowired
    private RamoRepository ramoRepository;

    public List<Ramo> findAll() {
        return ramoRepository.findAllByOrderByNomeAsc();
    }

    public Ramo findById(final Long id) {
        return ramoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id)
            ));
    }
    
    public List<Ramo> findByNomeContaining(final String nome) {
        var ramos = ramoRepository.findByNomeContaining(nome);
        if (ramos.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format(NOME_NOT_FOUND, nome)
            ); 
        }
        return ramos;
    }

    @Transactional
    public Ramo insert(final Ramo ramo) {
        var ramoFormatado = ramo;
        ramo.format();

        if (ramoRepository.existsByNome(ramo.getNome())) {
            throw new DuplicateKeyException(
                String.format(ALREADY_EXISTS, ramo.getNome()));
        }
        return ramoRepository.save(ramoFormatado);
    }

    @Transactional
    public void delete(final Long id) {
        ramoRepository.delete(this.findById(id));
    }

    @Transactional
    public Ramo update(final Long id, final Ramo ramo) {
        var ramoFormatado = ramo;
        ramo.format();
        
        var ramoRecovered = this.findById(id);
        if (!ramoRecovered.getNome().equals(ramoFormatado.getNome()) &&
            ramoRepository.existsByNome(ramo.getNome())) {
                throw new DuplicateKeyException(
                    String.format(ALREADY_EXISTS, ramo.getNome()));   
        }
        ramoFormatado.setId(id);
        ramoFormatado.setDataCriacao(ramoRecovered.getDataCriacao());
        ramoFormatado.setDataAtualizacao(OffsetDateTime.now());
        return ramoRepository.save(ramoFormatado);
    }

}