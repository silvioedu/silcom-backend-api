package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.ProdutoOutputDTO;
import com.silcom.manager.domain.model.Produto;
import com.silcom.manager.domain.model.ProdutoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoOutputAssemblerTest {
    
    @Autowired
    private ProdutoOutputAssembler assembler;

    @Test
    void shouldConvertProdutoModel_inProdutoOutput() {
        Produto model = ProdutoMock.getInstance();
        ProdutoOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListProdutoModel_inListProdutoOutput() {
        int max = 10;
        List<Produto> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            Produto model = ProdutoMock.getInstance();
            modelList.add(model);
        }

        List<ProdutoOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(Produto origin, ProdutoOutputDTO destination) {
        assertEquals(origin.getComplemento().getNome(), destination.getComplementoNome());
        assertEquals(origin.getCor().getNome(), destination.getCorNome());
        assertEquals(origin.getDetalhe().getNome(), destination.getDetalheNome());
        assertEquals(origin.getFabricante().getNome(), destination.getFabricanteNome());
        assertEquals(origin.getTipo().getNome(), destination.getTipoNome());
        assertEquals(origin.getCodigo(), destination.getCodigo());
        assertEquals(origin.getFolder(), destination.getFolder());
        assertEquals(origin.getPreco(), destination.getPreco());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }

}
