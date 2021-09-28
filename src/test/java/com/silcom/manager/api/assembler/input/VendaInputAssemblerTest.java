package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.VendaInputDTO;
import com.silcom.manager.domain.model.Venda;
import com.silcom.manager.domain.model.VendaMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VendaInputAssemblerTest {
    
    @Autowired
    private VendaInputAssembler assembler;

    @Test
    void shouldConvertVendaInput_inVendaModel() {
        VendaInputDTO input = VendaMock.getInputInstance();
        Venda model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(VendaInputDTO origin, Venda destination) {
        assertEquals(origin.getFormaPagamentoTipoId(), destination.getFormaPagamentoTipo().getId());
        assertEquals(origin.getDesconto(), destination.getDesconto());
        assertEquals(origin.getAgravo(), destination.getAgravo());
        assertEquals(origin.getValorTotal(), destination.getValorTotal());
        assertEquals(origin.getObservacoes(), destination.getObservacoes());
    }
}
