package com.silcom.manager.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.ClienteVendaItemInputDTO;

import org.apache.commons.lang3.RandomUtils;

public class ClienteVendaItemMock {
    
    public static ClienteVendaItem getInstance() {
        ClienteVendaItem venda = new ClienteVendaItem();
        venda.setId(RandomUtils.nextLong(1, 24));
        venda.setClienteVenda(ClienteVendaMock.getInstance());
        venda.setProduto(ProdutoMock.getRealInstance());
        venda.setTamanho(RandomUtils.nextInt(35, 45));
        venda.setQuantidade(2);
        venda.setValorUnitario(BigDecimal.valueOf(27.20));
        venda.setDataCriacao(OffsetDateTime.now());
        venda.setDataAtualizacao(OffsetDateTime.now());
        return venda;
    }

    public static ClienteVendaItemInputDTO getInputInstance() {
        ClienteVendaItem venda = getInstance();
        ClienteVendaItemInputDTO input = new ClienteVendaItemInputDTO();
        input.setProdutoId(venda.getProduto().getId());
        input.setTamanho(venda.getTamanho());;
        input.setQuantidade(venda.getQuantidade());
        input.setValorUnitario(venda.getValorUnitario());
        return input;
    }

}
