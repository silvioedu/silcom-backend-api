package com.silcom.manager.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.DuplicateKeyException;
import com.silcom.manager.domain.exception.ResourceInUseException;
import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.ProdutoDetalhe;
import com.silcom.manager.domain.repository.ProdutoDetalheRepository;
import com.silcom.manager.domain.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoDetalheService {
    
    private static final String ID_IN_USE = "Produto fabricante id %d em uso";
    private static final String ALREADY_EXISTS = "Produto detalhe nome '%s' ou sigla '%s' já existe";
    private static final String NOME_NOT_FOUND = "Não foram encontrados cpres de produto com o nome '%s'";
    private static final String ID_NOT_FOUND = "Produto detalhe id %d não encontrado";

    @Autowired
    private ProdutoDetalheRepository produtoDetalheRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDetalhe> findAll() {
        return produtoDetalheRepository.findAllByOrderByNomeAsc();
    }

    public ProdutoDetalhe findById(final Long id) {
        return produtoDetalheRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id)
            ));
    }
    
    public List<ProdutoDetalhe> findByNomeContaining(final String nome) {
        var produtoDetalhes = produtoDetalheRepository.findByNomeContainingIgnoreCase(nome);
        if (produtoDetalhes.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format(NOME_NOT_FOUND, nome)
            ); 
        }
        return produtoDetalhes;
    }

    @Transactional
    public ProdutoDetalhe insert(final ProdutoDetalhe produtoDetalhe) {
        var produtoDetalheFormatado = produtoDetalhe;
        produtoDetalheFormatado.format();

        if (produtoDetalheRepository.existsByNomeIgnoreCase(produtoDetalhe.getNome()) ||
            produtoDetalheRepository.existsBySiglaIgnoreCase(produtoDetalhe.getSigla())) {
            throw new DuplicateKeyException(
                String.format(ALREADY_EXISTS, produtoDetalhe.getNome(), produtoDetalhe.getSigla()));
        }
        return produtoDetalheRepository.save(produtoDetalheFormatado);
    }

    @Transactional
    public void delete(final Long id) {
        if (produtoRepository.existsByDetalheId(id)) {
            throw new ResourceInUseException(
                String.format(ID_IN_USE, id));
        }
        produtoDetalheRepository.delete(this.findById(id));
    }

    @Transactional
    public ProdutoDetalhe update(final Long id, final ProdutoDetalhe produtoDetalhe) {
        var produtoDetalheFormatado = produtoDetalhe;
        produtoDetalheFormatado.format();
        
        var produtoDetalheRecovered = this.findById(id);
        if ((!produtoDetalheRecovered.getNome().equals(produtoDetalheFormatado.getNome()) &&
            produtoDetalheRepository.existsByNomeIgnoreCase(produtoDetalhe.getNome()) ) ||
            (!produtoDetalheRecovered.getSigla().equals(produtoDetalheFormatado.getSigla()) &&
            produtoDetalheRepository.existsBySiglaIgnoreCase(produtoDetalhe.getSigla()))) {
                throw new DuplicateKeyException(
                    String.format(ALREADY_EXISTS, produtoDetalhe.getNome(), produtoDetalhe.getSigla()));   
        }
        produtoDetalheFormatado.setId(id);
        produtoDetalheFormatado.setDataCriacao(produtoDetalheRecovered.getDataCriacao());
        produtoDetalheFormatado.setDataAtualizacao(OffsetDateTime.now());
        return produtoDetalheRepository.save(produtoDetalheFormatado);
    }

}