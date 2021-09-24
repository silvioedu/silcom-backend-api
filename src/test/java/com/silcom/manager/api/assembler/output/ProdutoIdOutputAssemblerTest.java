package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.ProdutoIdOutputDTO;
import com.silcom.manager.domain.model.Produto;
import com.silcom.manager.domain.model.ProdutoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoIdOutputAssemblerTest {
    
    @Autowired
    private ProdutoIdOutputAssembler assembler;

    @Test
    void shouldConvertProdutoIdModel_inProdutoIdOutput() {
        Produto model = ProdutoMock.getInstance();
        ProdutoIdOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListProdutoIdModel_inListProdutoIdOutput() {
        int max = 10;
        List<Produto> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            Produto model = ProdutoMock.getInstance();
            modelList.add(model);
        }

        List<ProdutoIdOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(Produto origin, ProdutoIdOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getComplemento().getId(), destination.getComplementoId());
        assertEquals(origin.getCor().getId(), destination.getCorId());
        assertEquals(origin.getDetalhe().getId(), destination.getDetalheId());
        assertEquals(origin.getFabricante().getId(), destination.getFabricanteId());
        assertEquals(origin.getTipo().getId(), destination.getTipoId());
        assertEquals(origin.getCodigo(), destination.getCodigo());
        assertEquals(origin.getFolder(), destination.getFolder());
        assertEquals(origin.getPreco(), destination.getPreco());
    }

}
