package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.VendaInputDTO;
import com.silcom.manager.domain.model.FormaPagamentoTipo;
import com.silcom.manager.domain.model.Venda;

import org.springframework.stereotype.Component;

@Component
public class VendaInputAssembler {
    
    public Venda toModel(VendaInputDTO venda) {
        FormaPagamentoTipo formaPagamentoTipo = new FormaPagamentoTipo();
        formaPagamentoTipo.setId(venda.getFormaPagamentoTipoId());

        return Venda.builder()
            .formaPagamentoTipo(formaPagamentoTipo)
            .desconto(venda.getDesconto())
            .agravo(venda.getAgravo())
            .valorTotal(venda.getValorTotal())
            .emitirNota(venda.isEmitirNota())
            .observacoes(venda.getObservacoes())
            .build();
    }

}
