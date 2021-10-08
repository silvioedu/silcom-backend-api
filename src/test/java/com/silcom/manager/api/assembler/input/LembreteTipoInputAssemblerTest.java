package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.LembreteTipoInputDTO;
import com.silcom.manager.domain.model.LembreteTipo;
import com.silcom.manager.domain.model.LembreteTipoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LembreteTipoInputAssemblerTest {
    
    @Autowired
    private LembreteTipoInputAssembler assembler;

    @Test
    void shouldConvertLembreteTipoInput_inLembreteTipoModel() {
        LembreteTipoInputDTO input = LembreteTipoMock.getInputInstance();
        LembreteTipo model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(LembreteTipoInputDTO origin, LembreteTipo destination) {
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getIntervalo(), destination.getIntervalo());
    }
}
