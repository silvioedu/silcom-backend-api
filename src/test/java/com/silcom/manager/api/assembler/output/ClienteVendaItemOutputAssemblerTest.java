package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.ClienteVendaItemOutputDTO;
import com.silcom.manager.domain.model.ClienteVendaItem;
import com.silcom.manager.domain.model.ClienteVendaItemMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteVendaItemOutputAssemblerTest {
    
    @Autowired
    private ClienteVendaItemOutputAssembler assembler;

    @Test
    void shouldConvertVendaItemModel_inVendaItemOutput() {
        ClienteVendaItem model = ClienteVendaItemMock.getInstance();
        ClienteVendaItemOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListVendaItemModel_inListVendaItemOutput() {
        int max = 10;
        List<ClienteVendaItem> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            ClienteVendaItem model = ClienteVendaItemMock.getInstance();
            modelList.add(model);
        }

        List<ClienteVendaItemOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(ClienteVendaItem origin, ClienteVendaItemOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getProduto().getCodigo(), destination.getProdutoCodigo());
        assertEquals(origin.getQuantidade(), destination.getQuantidade());
        assertEquals(origin.getValorUnitario(), destination.getValorUnitario());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
