package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.ProdutoInputDTO;
import com.silcom.manager.domain.model.Produto;
import com.silcom.manager.domain.model.ProdutoComplemento;
import com.silcom.manager.domain.model.ProdutoCor;
import com.silcom.manager.domain.model.ProdutoDetalhe;
import com.silcom.manager.domain.model.ProdutoFabricante;
import com.silcom.manager.domain.model.ProdutoTipo;

import org.springframework.stereotype.Component;

@Component
public class ProdutoInputAssembler {
    
    public Produto toModel(ProdutoInputDTO produto) {
        ProdutoTipo produtoTipo = new ProdutoTipo();
        produtoTipo.setId(produto.getTipoId());

        ProdutoDetalhe produtoDetalhe = new ProdutoDetalhe();
        produtoDetalhe.setId(produto.getDetalheId());

        ProdutoComplemento produtoComplemento = new ProdutoComplemento();
        produtoComplemento.setId(produto.getComplementoId());

        ProdutoCor produtoCor = new ProdutoCor();
        produtoCor.setId(produto.getCorId());

        ProdutoFabricante produtoFabricante = new ProdutoFabricante();
        produtoFabricante.setId(produto.getFabricanteId());

        return Produto.builder()
            .tipo(produtoTipo)
            .detalhe(produtoDetalhe)
            .complemento(produtoComplemento)
            .cor(produtoCor)
            .fabricante(produtoFabricante)
            .folder(produto.getFolder())
            .preco(produto.getPreco())
            .build();
    }

}
