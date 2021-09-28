package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.ClienteVendaInputDTO;
import com.silcom.manager.domain.model.FormaPagamentoTipo;
import com.silcom.manager.domain.model.ClienteVenda;

import org.springframework.stereotype.Component;

@Component
public class ClienteVendaInputAssembler {
    
    public ClienteVenda toModel(ClienteVendaInputDTO venda) {
        FormaPagamentoTipo formaPagamentoTipo = new FormaPagamentoTipo();
        formaPagamentoTipo.setId(venda.getFormaPagamentoTipoId());

        return ClienteVenda.builder()
            .formaPagamentoTipo(formaPagamentoTipo)
            .desconto(venda.getDesconto())
            .agravo(venda.getAgravo())
            .valorTotal(venda.getValorTotal())
            .emitirNota(venda.isEmitirNota())
            .observacoes(venda.getObservacoes())
            .build();
    }

}
