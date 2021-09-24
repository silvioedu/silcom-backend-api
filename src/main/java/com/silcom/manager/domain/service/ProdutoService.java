package com.silcom.manager.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.DuplicateKeyException;
import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.Produto;
import com.silcom.manager.domain.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
    private static final String ALREADY_EXISTS = "Produto código '%s' já existe";
    private static final String ID_NOT_FOUND = "Produto id %d não encontrado";

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoTipoService produtoTipoService;

    @Autowired
    private ProdutoCorService produtoCorService;

    @Autowired
    private ProdutoDetalheService produtoDetalheService;

    @Autowired
    private ProdutoComplementoService produtoComplementoService;

    @Autowired
    private ProdutoFabricanteService produtoFabricanteService;

    public List<Produto> findAll() {
        return produtoRepository.findAllByOrderByIdAsc();
    }

    public Produto findById(final Long id) {
        return produtoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id)
            ));
    }
    
    @Transactional
    public Produto insert(final Produto produto) {
        Produto newProduto = createProduto(produto);
        newProduto.format();
        
        if (produtoRepository.existsByCodigoIgnoreCase(newProduto.getCodigo())) {
            throw new DuplicateKeyException(
                String.format(ALREADY_EXISTS, newProduto.getCodigo()));
        }
        return produtoRepository.save(newProduto);
    }

    @Transactional
    public void delete(final Long id) {
        produtoRepository.delete(this.findById(id));
    }

    @Transactional
    public Produto update(final Long id, final Produto produto) {
        Produto newProduto = createProduto(produto);
        newProduto.format();

        var produtoRecovered = this.findById(id);
        if ((!produtoRecovered.getCodigo().equals(newProduto.getCodigo()) &&
            produtoRepository.existsByCodigoIgnoreCase(produto.getCodigo()))) {
                throw new DuplicateKeyException(
                    String.format(ALREADY_EXISTS, produto.getCodigo()));   
        }
        newProduto.setId(id);
        newProduto.setDataCriacao(produtoRecovered.getDataCriacao());
        newProduto.setDataAtualizacao(OffsetDateTime.now());
        return produtoRepository.save(newProduto);
    }

    private Produto createProduto(final Produto produto) {
        Produto newProduto = Produto.builder()
            .tipo(produtoTipoService.findById(produto.getTipo().getId()))
            .cor(produtoCorService.findById(produto.getCor().getId()))
            .detalhe(produtoDetalheService.findById(produto.getDetalhe().getId()))
            .complemento(produtoComplementoService.findById(produto.getComplemento().getId()))
            .fabricante(produtoFabricanteService.findById(produto.getFabricante().getId()))
            .folder(produto.getFolder())
            .preco(produto.getPreco())
            .build();

        newProduto.geraCodigo();

        return newProduto;
    }

}