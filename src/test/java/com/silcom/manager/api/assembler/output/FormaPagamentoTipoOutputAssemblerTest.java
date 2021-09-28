package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.FormaPagamentoTipoOutputDTO;
import com.silcom.manager.domain.model.FormaPagamentoTipo;
import com.silcom.manager.domain.model.FormaPagamentoTipoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FormaPagamentoTipoOutputAssemblerTest {
    
    @Autowired
    private FormaPagamentoTipoOutputAssembler assembler;

    @Test
    void shouldConvertFormaPagamentoTipoModel_inFormaPagamentoTipoOutput() {
        FormaPagamentoTipo model = FormaPagamentoTipoMock.getInstance();
        FormaPagamentoTipoOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListFormaPagamentoTipoModel_inListFormaPagamentoTipoOutput() {
        int max = 10;
        List<FormaPagamentoTipo> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            FormaPagamentoTipo model = FormaPagamentoTipoMock.getInstance();
            modelList.add(model);
        }

        List<FormaPagamentoTipoOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(FormaPagamentoTipo origin, FormaPagamentoTipoOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getDesconto(), destination.getDesconto());
        assertEquals(origin.getAgravo(), destination.getAgravo());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
