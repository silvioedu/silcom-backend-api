package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.ClienteVendaOutputDTO;
import com.silcom.manager.domain.model.ClienteVenda;
import com.silcom.manager.domain.model.ClienteVendaMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteVendaOutputAssemblerTest {
    
    @Autowired
    private ClienteVendaOutputAssembler assembler;

    @Test
    void shouldConvertVendaModel_inVendaOutput() {
        ClienteVenda model = ClienteVendaMock.getInstance();
        ClienteVendaOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListVendaModel_inListVendaOutput() {
        int max = 10;
        List<ClienteVenda> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            ClienteVenda model = ClienteVendaMock.getInstance();
            modelList.add(model);
        }

        List<ClienteVendaOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(ClienteVenda origin, ClienteVendaOutputDTO destination) {
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
