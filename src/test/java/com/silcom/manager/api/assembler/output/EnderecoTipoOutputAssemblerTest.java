package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.EnderecoTipoOutputDTO;
import com.silcom.manager.domain.model.EnderecoTipo;
import com.silcom.manager.domain.model.EnderecoTipoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EnderecoTipoOutputAssemblerTest {
    
    @Autowired
    private EnderecoTipoOutputAssembler assembler;

    @Test
    void shouldConvertEnderecoTipoModel_inEnderecoTipoOutput() {
        EnderecoTipo model = EnderecoTipoMock.getInstance();
        EnderecoTipoOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListEnderecoTipoModel_inListEnderecoTipoOutput() {
        int max = 10;
        List<EnderecoTipo> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            EnderecoTipo model = EnderecoTipoMock.getInstance();
            modelList.add(model);
        }

        List<EnderecoTipoOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(EnderecoTipo origin, EnderecoTipoOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
