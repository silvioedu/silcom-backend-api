package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.ClienteVendaItemInputDTO;
import com.silcom.manager.domain.model.Produto;
import com.silcom.manager.domain.model.ClienteVendaItem;

import org.springframework.stereotype.Component;

@Component
public class ClienteVendaItemInputAssembler {
    
    public ClienteVendaItem toModel(ClienteVendaItemInputDTO vendaItem) {
        Produto produto = Produto.builder()
            .id(vendaItem.getProdutoId())
            .build();
            
        return ClienteVendaItem.builder()
            .produto(produto)
            .quantidade(vendaItem.getQuantidade())
            .valorUnitario(vendaItem.getValorUnitario())
            .build();
    }

}
