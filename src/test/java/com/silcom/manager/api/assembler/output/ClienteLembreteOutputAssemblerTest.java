package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.ClienteLembreteOutputDTO;
import com.silcom.manager.domain.model.ClienteLembrete;
import com.silcom.manager.domain.model.ClienteLembreteMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteLembreteOutputAssemblerTest {
    
    @Autowired
    private ClienteLembreteOutputAssembler assembler;

    @Test
    void shouldConvertClienteLembreteModel_inClienteLembreteOutput() {
        ClienteLembrete model = ClienteLembreteMock.getInstance();
        ClienteLembreteOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListClienteLembreteModel_inListClienteLembreteOutput() {
        int max = 10;
        List<ClienteLembrete> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            ClienteLembrete model = ClienteLembreteMock.getInstance();
            modelList.add(model);
        }

        List<ClienteLembreteOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(ClienteLembrete origin, ClienteLembreteOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getLembreteTipo().getNome(), destination.getTipoLembreteNome());
        assertEquals(origin.getDataEvento(), destination.getDataEvento());
        assertEquals(origin.getObservacoes(), destination.getObservacoes());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
