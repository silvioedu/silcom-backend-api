package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.ProdutoCorInputDTO;
import com.silcom.manager.domain.model.ProdutoCor;
import com.silcom.manager.domain.model.ProdutoCorMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoCorInputAssemblerTest {
    
    @Autowired
    private ProdutoCorInputAssembler assembler;

    @Test
    void shouldConvertProdutoCorInput_inProdutoCorModel() {
        ProdutoCorInputDTO input = ProdutoCorMock.getInputInstance();
        ProdutoCor model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(ProdutoCorInputDTO origin, ProdutoCor destination) {
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getSigla(), destination.getSigla());
    }
}
