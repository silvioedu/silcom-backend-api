package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.ContatoTipoOutputDTO;
import com.silcom.manager.domain.model.ContatoTipo;
import com.silcom.manager.domain.model.ContatoTipoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContatoTipoOutputAssemblerTest {
    
    @Autowired
    private ContatoTipoOutputAssembler assembler;

    @Test
    void shouldConvertContatoTipoModel_inContatoTipoOutput() {
        ContatoTipo model = ContatoTipoMock.getInstance();
        ContatoTipoOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListContatoTipoModel_inListContatoTipoOutput() {
        int max = 10;
        List<ContatoTipo> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            ContatoTipo model = ContatoTipoMock.getInstance();
            modelList.add(model);
        }

        List<ContatoTipoOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(ContatoTipo origin, ContatoTipoOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
