package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.ClienteVendaInputDTO;
import com.silcom.manager.domain.model.ClienteVenda;
import com.silcom.manager.domain.model.ClienteVendaMock;
import com.silcom.manager.domain.model.VendaStatus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteVendaInputAssemblerTest {
    
    @Autowired
    private ClienteVendaInputAssembler assembler;

    @Test
    void shouldConvertVendaInput_inVendaModel() {
        ClienteVendaInputDTO input = ClienteVendaMock.getInputInstance();
        ClienteVenda model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(ClienteVendaInputDTO origin, ClienteVenda destination) {
        assertEquals(origin.getFormaPagamentoTipoId(), destination.getFormaPagamentoTipo().getId());
        assertEquals(origin.getDesconto(), destination.getDesconto());
        assertEquals(origin.getAgravo(), destination.getAgravo());
        assertEquals(origin.getValorTotal(), destination.getValorTotal());
        assertEquals(origin.getObservacoes(), destination.getObservacoes());
        assertEquals(VendaStatus.CRIADO, destination.getStatus());
    }
}
