package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.ProdutoFabricanteInputDTO;
import com.silcom.manager.domain.model.ProdutoFabricante;
import com.silcom.manager.domain.model.ProdutoFabricanteMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoFabricanteInputAssemblerTest {
    
    @Autowired
    private ProdutoFabricanteInputAssembler assembler;

    @Test
    void shouldConvertProdutoFabricanteInput_inProdutoFabricanteModel() {
        ProdutoFabricanteInputDTO input = ProdutoFabricanteMock.getInputInstance();
        ProdutoFabricante model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(ProdutoFabricanteInputDTO origin, ProdutoFabricante destination) {
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getSigla(), destination.getSigla());
    }
}
