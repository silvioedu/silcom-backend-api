package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.ProdutoTipoInputDTO;

import org.apache.commons.lang3.RandomUtils;

public class ProdutoTipoMock {
    
    public static ProdutoTipo getInstance() {
        ProdutoTipo produtoTipo = new ProdutoTipo();
        produtoTipo.setId(RandomUtils.nextLong(1, 24));
        produtoTipo.setNome("Nome Tipo " + produtoTipo.getId());
        produtoTipo.setSigla("D");
        produtoTipo.setDataCriacao(OffsetDateTime.now());

        return produtoTipo;
    }

    public static ProdutoTipoInputDTO getInputInstance() {
        ProdutoTipo produtoTipo = getInstance();
        ProdutoTipoInputDTO input = new ProdutoTipoInputDTO();
        input.setNome(produtoTipo.getNome());
        input.setSigla(produtoTipo.getSigla());
        return input;
    }

    public static ProdutoTipo getRealInstance() {
        ProdutoTipo produtoTipo = new ProdutoTipo();
        produtoTipo.setId(1L);
        produtoTipo.setNome("Bota");
        produtoTipo.setSigla("B");
        return produtoTipo;
    }

}
