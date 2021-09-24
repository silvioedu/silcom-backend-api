package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.ProdutoDetalheInputDTO;
import com.silcom.manager.domain.model.ProdutoDetalhe;
import com.silcom.manager.domain.model.ProdutoDetalheMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoDetalheInputAssemblerTest {
    
    @Autowired
    private ProdutoDetalheInputAssembler assembler;

    @Test
    void shouldConvertProdutoDetalheInput_inProdutoDetalheModel() {
        ProdutoDetalheInputDTO input = ProdutoDetalheMock.getInputInstance();
        ProdutoDetalhe model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(ProdutoDetalheInputDTO origin, ProdutoDetalhe destination) {
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getSigla(), destination.getSigla());
    }
}
