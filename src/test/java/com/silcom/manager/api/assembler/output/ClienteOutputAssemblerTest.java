package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.ClienteOutputDTO;
import com.silcom.manager.domain.model.Cliente;
import com.silcom.manager.domain.model.ClienteMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteOutputAssemblerTest {
    
    @Autowired
    private ClienteOutputAssembler assembler;

    @Test
    void shouldConvertClienteModel_inClienteOutput() {
        Cliente model = ClienteMock.getInstance();
        ClienteOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListClienteModel_inListClienteOutput() {
        int max = 10;
        List<Cliente> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            Cliente model = ClienteMock.getInstance();
            modelList.add(model);
        }

        List<ClienteOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(Cliente origin, ClienteOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getRazaoSocial(), destination.getRazaoSocial());
        assertEquals(origin.getNomeFantasia(), destination.getNomeFantasia());
        assertEquals(origin.getRamo().getNome(), destination.getRamoNome());
        assertEquals(origin.getTipoPessoa(), destination.getTipoPessoa());
        assertEquals(origin.getObservacoes(), destination.getObservacoes());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
