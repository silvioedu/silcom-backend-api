package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.ProdutoCorOutputDTO;
import com.silcom.manager.domain.model.ProdutoCor;
import com.silcom.manager.domain.model.ProdutoCorMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoCorOutputAssemblerTest {
    
    @Autowired
    private ProdutoCorOutputAssembler assembler;

    @Test
    void shouldConvertProdutoCorModel_inProdutoCorOutput() {
        ProdutoCor model = ProdutoCorMock.getInstance();
        ProdutoCorOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListProdutoCorModel_inListProdutoCorOutput() {
        int max = 10;
        List<ProdutoCor> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            ProdutoCor model = ProdutoCorMock.getInstance();
            modelList.add(model);
        }

        List<ProdutoCorOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(ProdutoCor origin, ProdutoCorOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getSigla(), destination.getSigla());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
