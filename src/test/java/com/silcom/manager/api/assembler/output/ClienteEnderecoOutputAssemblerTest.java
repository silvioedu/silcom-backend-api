package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.ClienteEnderecoOutputDTO;
import com.silcom.manager.domain.model.ClienteEndereco;
import com.silcom.manager.domain.model.ClienteEnderecoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteEnderecoOutputAssemblerTest {
    
    @Autowired
    private ClienteEnderecoOutputAssembler assembler;

    @Test
    void shouldConvertClienteEnderecoModel_inClienteEnderecoOutput() {
        ClienteEndereco model = ClienteEnderecoMock.getInstance();
        ClienteEnderecoOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListClienteEnderecoModel_inListClienteEnderecoOutput() {
        int max = 10;
        List<ClienteEndereco> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            ClienteEndereco model = ClienteEnderecoMock.getInstance();
            modelList.add(model);
        }

        List<ClienteEnderecoOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(ClienteEndereco origin, ClienteEnderecoOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getCep(), destination.getCep());
        assertEquals(origin.getLogradouro(), destination.getLogradouro());
        assertEquals(origin.getNumero(), destination.getNumero());
        assertEquals(origin.getComplemento(), destination.getComplemento());
        assertEquals(origin.getBairro(), destination.getBairro());
        assertEquals(origin.getCidade(), destination.getCidade());
        assertEquals(origin.getEstado(), destination.getEstado());
        assertEquals(origin.getObservacoes(), destination.getObservacoes());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
