package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.FormaPagamentoTipoInputDTO;
import com.silcom.manager.domain.model.FormaPagamentoTipo;
import com.silcom.manager.domain.model.FormaPagamentoTipoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FormaPagamentoTipoInputAssemblerTest {
    
    @Autowired
    private FormaPagamentoTipoInputAssembler assembler;

    @Test
    void shouldConvertFormaPagamentoTipoInput_inFormaPagamentoTipoModel() {
        FormaPagamentoTipoInputDTO input = FormaPagamentoTipoMock.getInputInstance();
        FormaPagamentoTipo model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(FormaPagamentoTipoInputDTO origin, FormaPagamentoTipo destination) {
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getDesconto(), destination.getDesconto());
        assertEquals(origin.getAgravo(), destination.getAgravo());
    }
}
