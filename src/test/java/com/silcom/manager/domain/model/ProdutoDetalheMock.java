package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.ProdutoDetalheInputDTO;

import org.apache.commons.lang3.RandomUtils;

public class ProdutoDetalheMock {
    
    public static ProdutoDetalhe getInstance() {
        ProdutoDetalhe produtoDetalhe = new ProdutoDetalhe();
        produtoDetalhe.setId(RandomUtils.nextLong(1, 24));
        produtoDetalhe.setNome("Nome Detalhe " + produtoDetalhe.getId());
        produtoDetalhe.setSigla("Z");
        produtoDetalhe.setDataCriacao(OffsetDateTime.now());

        return produtoDetalhe;
    }

    public static ProdutoDetalheInputDTO getInputInstance() {
        ProdutoDetalhe produtoDetalhe = getInstance();
        ProdutoDetalheInputDTO input = new ProdutoDetalheInputDTO();
        input.setNome(produtoDetalhe.getNome());
        input.setSigla(produtoDetalhe.getSigla());
        return input;
    }

    public static ProdutoDetalhe getRealInstance() {
        ProdutoDetalhe produtoDetalhe = new ProdutoDetalhe();
        produtoDetalhe.setId(1L);
        produtoDetalhe.setNome("Cadarço");
        produtoDetalhe.setSigla("A");
        return produtoDetalhe;
    }

}
