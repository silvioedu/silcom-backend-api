package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.ProdutoCorInputDTO;

import org.apache.commons.lang3.RandomUtils;

public class ProdutoCorMock {
    
    public static ProdutoCor getInstance() {
        ProdutoCor produtoCor = new ProdutoCor();
        produtoCor.setId(RandomUtils.nextLong(1, 24));
        produtoCor.setNome("Nome Cor " + produtoCor.getId());
        produtoCor.setSigla("Q");
        produtoCor.setDataCriacao(OffsetDateTime.now());

        return produtoCor;
    }

    public static ProdutoCorInputDTO getInputInstance() {
        ProdutoCor produtoCor = getInstance();
        ProdutoCorInputDTO input = new ProdutoCorInputDTO();
        input.setNome(produtoCor.getNome());
        input.setSigla(produtoCor.getSigla());
        return input;
    }

    public static ProdutoCor getRealInstance() {
        ProdutoCor produtoCor = new ProdutoCor();
        produtoCor.setId(6L);
        produtoCor.setNome("Rosa");
        produtoCor.setSigla("R");
        return produtoCor;
    }

}
