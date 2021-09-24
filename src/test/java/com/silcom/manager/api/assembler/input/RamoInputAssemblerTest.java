package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.RamoInputDTO;
import com.silcom.manager.domain.model.Ramo;
import com.silcom.manager.domain.model.RamoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RamoInputAssemblerTest {
    
    @Autowired
    private RamoInputAssembler assembler;

    @Test
    void shouldConvertRamoInput_inRamoModel() {
        RamoInputDTO input = RamoMock.getInputInstance();
        Ramo model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(RamoInputDTO origin, Ramo destination) {
        assertEquals(origin.getNome(), destination.getNome());
    }
}
