package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.ProdutoFabricanteOutputDTO;
import com.silcom.manager.domain.model.ProdutoFabricante;
import com.silcom.manager.domain.model.ProdutoFabricanteMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoFabricanteOutputAssemblerTest {
    
    @Autowired
    private ProdutoFabricanteOutputAssembler assembler;

    @Test
    void shouldConvertProdutoFabricanteModel_inProdutoFabricanteOutput() {
        ProdutoFabricante model = ProdutoFabricanteMock.getInstance();
        ProdutoFabricanteOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListProdutoFabricanteModel_inListProdutoFabricanteOutput() {
        int max = 10;
        List<ProdutoFabricante> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            ProdutoFabricante model = ProdutoFabricanteMock.getInstance();
            modelList.add(model);
        }

        List<ProdutoFabricanteOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(ProdutoFabricante origin, ProdutoFabricanteOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getSigla(), destination.getSigla());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
