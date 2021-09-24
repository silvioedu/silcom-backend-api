package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.ProdutoComplementoInputDTO;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class ProdutoComplementoMock {
    
    public static ProdutoComplemento getInstance() {
        ProdutoComplemento produtoComplemento = new ProdutoComplemento();
        produtoComplemento.setId(RandomUtils.nextLong(1, 24));
        produtoComplemento.setNome("Nome Complemento " + produtoComplemento.getId());
        produtoComplemento.setSigla(RandomStringUtils.randomAlphabetic(1));
        produtoComplemento.setDataCriacao(OffsetDateTime.now());

        return produtoComplemento;
    }

    public static ProdutoComplementoInputDTO getInputInstance() {
        ProdutoComplemento produtoComplemento = getInstance();
        ProdutoComplementoInputDTO input = new ProdutoComplementoInputDTO();
        input.setNome(produtoComplemento.getNome());
        input.setSigla(produtoComplemento.getSigla());
        return input;
    }

    public static ProdutoComplemento getRealInstance() {
        ProdutoComplemento produtoComplemento = new ProdutoComplemento();
        produtoComplemento.setId(6L);
        produtoComplemento.setNome("Monodensidade Sem bico");
        produtoComplemento.setSigla("0");
        return produtoComplemento;
    }

}
