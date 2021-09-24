package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.ProdutoTipoInputDTO;
import com.silcom.manager.domain.model.ProdutoTipo;
import com.silcom.manager.domain.model.ProdutoTipoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoTipoInputAssemblerTest {
    
    @Autowired
    private ProdutoTipoInputAssembler assembler;

    @Test
    void shouldConvertProdutoTipoInput_inProdutoTipoModel() {
        ProdutoTipoInputDTO input = ProdutoTipoMock.getInputInstance();
        ProdutoTipo model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(ProdutoTipoInputDTO origin, ProdutoTipo destination) {
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getSigla(), destination.getSigla());
    }
}
