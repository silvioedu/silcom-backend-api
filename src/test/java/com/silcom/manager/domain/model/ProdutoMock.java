package com.silcom.manager.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.ProdutoInputDTO;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class ProdutoMock {
    
    public static Produto getInstance() {
        Produto produto = Produto.builder()
            .id(RandomUtils.nextLong(1, 90))
            .complemento(ProdutoComplementoMock.getRealInstance())
            .cor(ProdutoCorMock.getRealInstance())
            .detalhe(ProdutoDetalheMock.getRealInstance())
            .fabricante(ProdutoFabricanteMock.getRealInstance())
            .tipo(ProdutoTipoMock.getRealInstance())
            .dataCriacao(OffsetDateTime.now())
            .dataAtualizacao(OffsetDateTime.now())
            .codigo(RandomStringUtils.randomAlphabetic(6).toUpperCase())
            .folder(RandomStringUtils.randomAlphabetic(2).toUpperCase())
            .preco(BigDecimal.valueOf(RandomUtils.nextLong(50, 100)/0.9).setScale(2, RoundingMode.HALF_UP))
            .build();
        return produto;
    }

    public static ProdutoInputDTO getInputInstance() {
        Produto produto = getInstance();
        ProdutoInputDTO input = new ProdutoInputDTO();
        input.setComplementoId(produto.getComplemento().getId());
        input.setCorId(produto.getCor().getId());
        input.setDetalheId(produto.getDetalhe().getId());
        input.setFabricanteId(produto.getFabricante().getId());
        input.setFolder(produto.getFolder());
        input.setPreco(produto.getPreco());
        input.setTipoId(produto.getTipo().getId());        
        return input;
    }

}
