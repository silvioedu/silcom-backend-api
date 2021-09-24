package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.ProdutoComplementoInputDTO;
import com.silcom.manager.domain.model.ProdutoComplemento;
import com.silcom.manager.domain.model.ProdutoComplementoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoComplementoInputAssemblerTest {
    
    @Autowired
    private ProdutoComplementoInputAssembler assembler;

    @Test
    void shouldConvertProdutoComplementoInput_inProdutoComplementoModel() {
        ProdutoComplementoInputDTO input = ProdutoComplementoMock.getInputInstance();
        ProdutoComplemento model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(ProdutoComplementoInputDTO origin, ProdutoComplemento destination) {
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getSigla(), destination.getSigla());
    }
}
