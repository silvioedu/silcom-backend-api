package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.ProdutoFabricanteInputDTO;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class ProdutoFabricanteMock {
    
    public static ProdutoFabricante getInstance() {
        ProdutoFabricante produtoFabricante = new ProdutoFabricante();
        produtoFabricante.setId(RandomUtils.nextLong(1, 24));
        produtoFabricante.setNome("Nome Fabricante " + produtoFabricante.getId());
        produtoFabricante.setSigla(RandomStringUtils.randomAlphabetic(2));
        produtoFabricante.setDataCriacao(OffsetDateTime.now());

        return produtoFabricante;
    }

    public static ProdutoFabricanteInputDTO getInputInstance() {
        ProdutoFabricante produtoFabricante = getInstance();
        ProdutoFabricanteInputDTO input = new ProdutoFabricanteInputDTO();
        input.setNome(produtoFabricante.getNome());
        input.setSigla(produtoFabricante.getSigla());
        return input;
    }

    public static ProdutoFabricante getRealInstance() {
        ProdutoFabricante produtoFabricante = new ProdutoFabricante();
        produtoFabricante.setId(1L);
        produtoFabricante.setNome("Susa");
        produtoFabricante.setSigla("02");
        return produtoFabricante;
    }

}
