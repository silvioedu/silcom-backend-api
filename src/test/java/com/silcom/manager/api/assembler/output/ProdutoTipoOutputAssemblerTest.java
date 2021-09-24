package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.ProdutoTipoOutputDTO;
import com.silcom.manager.domain.model.ProdutoTipo;
import com.silcom.manager.domain.model.ProdutoTipoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoTipoOutputAssemblerTest {
    
    @Autowired
    private ProdutoTipoOutputAssembler assembler;

    @Test
    void shouldConvertProdutoTipoModel_inProdutoTipoOutput() {
        ProdutoTipo model = ProdutoTipoMock.getInstance();
        ProdutoTipoOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListProdutoTipoModel_inListProdutoTipoOutput() {
        int max = 10;
        List<ProdutoTipo> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            ProdutoTipo model = ProdutoTipoMock.getInstance();
            modelList.add(model);
        }

        List<ProdutoTipoOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(ProdutoTipo origin, ProdutoTipoOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getSigla(), destination.getSigla());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
