package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.ProdutoDetalheOutputDTO;
import com.silcom.manager.domain.model.ProdutoDetalhe;
import com.silcom.manager.domain.model.ProdutoDetalheMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoDetalheOutputAssemblerTest {
    
    @Autowired
    private ProdutoDetalheOutputAssembler assembler;

    @Test
    void shouldConvertProdutoDetalheModel_inProdutoDetalheOutput() {
        ProdutoDetalhe model = ProdutoDetalheMock.getInstance();
        ProdutoDetalheOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListProdutoDetalheModel_inListProdutoDetalheOutput() {
        int max = 10;
        List<ProdutoDetalhe> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            ProdutoDetalhe model = ProdutoDetalheMock.getInstance();
            modelList.add(model);
        }

        List<ProdutoDetalheOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(ProdutoDetalhe origin, ProdutoDetalheOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getSigla(), destination.getSigla());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
