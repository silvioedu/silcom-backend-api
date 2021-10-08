package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.LembreteTipoOutputDTO;
import com.silcom.manager.domain.model.LembreteTipo;
import com.silcom.manager.domain.model.LembreteTipoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LembreteTipoOutputAssemblerTest {
    
    @Autowired
    private LembreteTipoOutputAssembler assembler;

    @Test
    void shouldConvertLembreteTipoModel_inLembreteTipoOutput() {
        LembreteTipo model = LembreteTipoMock.getInstance();
        LembreteTipoOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListLembreteTipoModel_inListLembreteTipoOutput() {
        int max = 10;
        List<LembreteTipo> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            LembreteTipo model = LembreteTipoMock.getInstance();
            modelList.add(model);
        }

        List<LembreteTipoOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(LembreteTipo origin, LembreteTipoOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getIntervalo(), destination.getIntervalo());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
