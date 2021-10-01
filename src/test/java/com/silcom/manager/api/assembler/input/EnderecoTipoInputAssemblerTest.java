package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.EnderecoTipoInputDTO;
import com.silcom.manager.domain.model.EnderecoTipo;
import com.silcom.manager.domain.model.EnderecoTipoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EnderecoTipoInputAssemblerTest {
    
    @Autowired
    private EnderecoTipoInputAssembler assembler;

    @Test
    void shouldConvertEnderecoTipoInput_inEnderecoTipoModel() {
        EnderecoTipoInputDTO input = EnderecoTipoMock.getInputInstance();
        EnderecoTipo model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(EnderecoTipoInputDTO origin, EnderecoTipo destination) {
        assertEquals(origin.getNome(), destination.getNome());
    }
}
