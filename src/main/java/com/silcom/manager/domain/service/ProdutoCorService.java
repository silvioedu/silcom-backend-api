package com.silcom.manager.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.DuplicateKeyException;
import com.silcom.manager.domain.exception.ResourceInUseException;
import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.ProdutoCor;
import com.silcom.manager.domain.repository.ProdutoCorRepository;
import com.silcom.manager.domain.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoCorService {
    
    private static final String ID_IN_USE = "Produto cor id %d em uso";
    private static final String ALREADY_EXISTS = "Produto cor nome '%s' ou sigla '%s' já existe";
    private static final String NOME_NOT_FOUND = "Não foram encontrados cores de produto com o nome '%s'";
    private static final String ID_NOT_FOUND = "Produto cor id %d não encontrado";

    @Autowired
    private ProdutoCorRepository produtoCorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoCor> findAll() {
        return produtoCorRepository.findAllByOrderByNomeAsc();
    }

    public ProdutoCor findById(final Long id) {
        return produtoCorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id)
            ));
    }
    
    public List<ProdutoCor> findByNomeContaining(final String nome) {
        var produtoCors = produtoCorRepository.findByNomeContainingIgnoreCase(nome);
        if (produtoCors.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format(NOME_NOT_FOUND, nome)
            ); 
        }
        return produtoCors;
    }

    @Transactional
    public ProdutoCor insert(final ProdutoCor produtoCor) {
        var produtoCorFormatado = produtoCor;
        produtoCorFormatado.format();

        if (produtoCorRepository.existsByNomeIgnoreCase(produtoCor.getNome()) ||
            produtoCorRepository.existsBySiglaIgnoreCase(produtoCor.getSigla())) {
            throw new DuplicateKeyException(
                String.format(ALREADY_EXISTS, produtoCor.getNome(), produtoCor.getSigla()));
        }
        return produtoCorRepository.save(produtoCorFormatado);
    }

    @Transactional
    public void delete(final Long id) {
        if (produtoRepository.existsByCorId(id)) {
            throw new ResourceInUseException(
                String.format(ID_IN_USE, id));
        }
        produtoCorRepository.delete(this.findById(id));
    }

    @Transactional
    public ProdutoCor update(final Long id, final ProdutoCor produtoCor) {
        var produtoCorFormatado = produtoCor;
        produtoCorFormatado.format();
        
        var produtoCorRecovered = this.findById(id);
        if ((!produtoCorRecovered.getNome().equals(produtoCorFormatado.getNome()) &&
            produtoCorRepository.existsByNomeIgnoreCase(produtoCor.getNome()) ) ||
            (!produtoCorRecovered.getSigla().equals(produtoCorFormatado.getSigla()) &&
            produtoCorRepository.existsBySiglaIgnoreCase(produtoCor.getSigla()))) {
                throw new DuplicateKeyException(
                    String.format(ALREADY_EXISTS, produtoCor.getNome(), produtoCor.getSigla()));   
        }
        produtoCorFormatado.setId(id);
        produtoCorFormatado.setDataCriacao(produtoCorRecovered.getDataCriacao());
        produtoCorFormatado.setDataAtualizacao(OffsetDateTime.now());
        return produtoCorRepository.save(produtoCorFormatado);
    }

}