package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.silcom.manager.api.dto.input.ProdutoInputDTO;
import com.silcom.manager.domain.model.Produto;
import com.silcom.manager.domain.model.ProdutoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoInputAssemblerTest {
    
    @Autowired
    private ProdutoInputAssembler assembler;

    @Test
    void shouldConvertProdutoInput_inProdutoModel() {
        ProdutoInputDTO input = ProdutoMock.getInputInstance();
        Produto model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(ProdutoInputDTO origin, Produto destination) {
        assertNotNull(origin.getComplementoId());
        assertNotNull(origin.getCorId());
        assertNotNull(origin.getDetalheId());
        assertNotNull(origin.getFabricanteId());
        assertNotNull(origin.getTipoId());
        assertEquals(origin.getFolder(), destination.getFolder());
        assertEquals(origin.getPreco(), destination.getPreco());
    }

}
