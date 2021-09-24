package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.ContatoTipoInputDTO;
import com.silcom.manager.domain.model.ContatoTipo;
import com.silcom.manager.domain.model.ContatoTipoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContatoTipoInputAssemblerTest {
    
    @Autowired
    private ContatoTipoInputAssembler assembler;

    @Test
    void shouldConvertContatoTipoInput_inContatoTipoModel() {
        ContatoTipoInputDTO input = ContatoTipoMock.getInputInstance();
        ContatoTipo model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(ContatoTipoInputDTO origin, ContatoTipo destination) {
        assertEquals(origin.getNome(), destination.getNome());
    }
}
