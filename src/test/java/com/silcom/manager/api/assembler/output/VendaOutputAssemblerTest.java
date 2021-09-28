package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.VendaOutputDTO;
import com.silcom.manager.domain.model.Venda;
import com.silcom.manager.domain.model.VendaMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VendaOutputAssemblerTest {
    
    @Autowired
    private VendaOutputAssembler assembler;

    @Test
    void shouldConvertVendaModel_inVendaOutput() {
        Venda model = VendaMock.getInstance();
        VendaOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListVendaModel_inListVendaOutput() {
        int max = 10;
        List<Venda> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            Venda model = VendaMock.getInstance();
            modelList.add(model);
        }

        List<VendaOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(Venda origin, VendaOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getFormaPagamentoTipo().getId(), destination.getFormaPagamentoTipoId());
        assertEquals(origin.getDesconto(), destination.getDesconto());
        assertEquals(origin.getAgravo(), destination.getAgravo());
        assertEquals(origin.getValorTotal(), destination.getValorTotal());
        assertEquals(origin.getObservacoes(), destination.getObservacoes());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
