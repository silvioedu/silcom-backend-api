package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.ClienteContatoOutputDTO;
import com.silcom.manager.domain.model.ClienteContato;
import com.silcom.manager.domain.model.ClienteContatoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteContatoOutputAssemblerTest {
    
    @Autowired
    private ClienteContatoOutputAssembler assembler;

    @Test
    void shouldConvertClienteContatoModel_inClienteContatoOutput() {
        ClienteContato model = ClienteContatoMock.getInstance();
        ClienteContatoOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListClienteContatoModel_inListClienteContatoOutput() {
        int max = 10;
        List<ClienteContato> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            ClienteContato model = ClienteContatoMock.getInstance();
            modelList.add(model);
        }

        List<ClienteContatoOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(ClienteContato origin, ClienteContatoOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getContatoTipo().getNome(), destination.getTipoContatoNome());
        assertEquals(origin.getContato(), destination.getContato());
        assertEquals(origin.getObservacoes(), destination.getObservacoes());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
