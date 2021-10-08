package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.ClienteLembreteInputDTO;
import com.silcom.manager.domain.model.ClienteLembrete;
import com.silcom.manager.domain.model.ClienteLembreteMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteLembreteInputAssemblerTest {
    
    @Autowired
    private ClienteLembreteInputAssembler assembler;

    @Test
    void shouldConvertClienteLembreteInput_inClienteLembreteModel() {
        ClienteLembreteInputDTO input = ClienteLembreteMock.getInputInstance();
        ClienteLembrete model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(ClienteLembreteInputDTO origin, ClienteLembrete destination) {
        assertEquals(origin.getTipoLembreteId(), destination.getLembreteTipo().getId());
        assertEquals(origin.getDataEvento(), destination.getDataEvento());
        assertEquals(origin.getObservacoes(), destination.getObservacoes());
    }
}
