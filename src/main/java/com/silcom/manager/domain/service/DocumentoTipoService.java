package com.silcom.manager.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.DuplicateKeyException;
import com.silcom.manager.domain.exception.ResourceInUseException;
import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.DocumentoTipo;
import com.silcom.manager.domain.repository.DocumentoTipoRepository;
import com.silcom.manager.domain.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoTipoService {
    
    private static final String ID_IN_USE = "Tipo de documento id %d em uso";
    private static final String ALREADY_EXISTS = "Tipo de documento nome '%s' já existe";
    private static final String NOME_NOT_FOUND = "Não foram encontrados tipos de documento com o nome '%s'";
    private static final String ID_NOT_FOUND = "Tipo de documento id %d não encontrado";

    @Autowired
    private DocumentoTipoRepository documentoTipoRepository;

    // @Autowired
    // private ProdutoRepository produtoRepository;

    public List<DocumentoTipo> findAll() {
        return documentoTipoRepository.findAllByOrderByNomeAsc();
    }

    public DocumentoTipo findById(final Long id) {
        return documentoTipoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id)
            ));
    }
    
    public List<DocumentoTipo> findByNomeContaining(final String nome) {
        var documentoTipos = documentoTipoRepository.findByNomeContainingIgnoreCase(nome);
        if (documentoTipos.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format(NOME_NOT_FOUND, nome)
            ); 
        }
        return documentoTipos;
    }

    @Transactional
    public DocumentoTipo insert(final DocumentoTipo documentoTipo) {
        var documentoTipoFormatado = documentoTipo;

        if (documentoTipoRepository.existsByNomeIgnoreCase(documentoTipo.getNome())) {
            throw new DuplicateKeyException(
                String.format(ALREADY_EXISTS, documentoTipo.getNome()));
        }
        return documentoTipoRepository.save(documentoTipoFormatado);
    }

    @Transactional
    public void delete(final Long id) {
        // if (produtoRepository.existsByCorId(id)) {
        //     throw new ResourceInUseException(
        //         String.format(ID_IN_USE, id));
        // }
        documentoTipoRepository.delete(this.findById(id));
    }

    @Transactional
    public DocumentoTipo update(final Long id, final DocumentoTipo documentoTipo) {
        var documentoTipoFormatado = documentoTipo;
        
        var documentoTipoRecovered = this.findById(id);
        if ((!documentoTipoRecovered.getNome().equals(documentoTipoFormatado.getNome()) &&
            documentoTipoRepository.existsByNomeIgnoreCase(documentoTipo.getNome()))) {
                throw new DuplicateKeyException(
                    String.format(ALREADY_EXISTS, documentoTipo.getNome()));   
        }
        documentoTipoFormatado.setId(id);
        documentoTipoFormatado.setDataCriacao(documentoTipoRecovered.getDataCriacao());
        documentoTipoFormatado.setDataAtualizacao(OffsetDateTime.now());
        return documentoTipoRepository.save(documentoTipoFormatado);
    }

}