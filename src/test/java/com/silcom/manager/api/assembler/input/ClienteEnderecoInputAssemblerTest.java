package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.ClienteEnderecoInputDTO;
import com.silcom.manager.domain.model.ClienteEndereco;
import com.silcom.manager.domain.model.ClienteEnderecoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteEnderecoInputAssemblerTest {
    
    @Autowired
    private ClienteEnderecoInputAssembler assembler;

    @Test
    void shouldConvertClienteEnderecoInput_inClienteEnderecoModel() {
        ClienteEnderecoInputDTO input = ClienteEnderecoMock.getInputInstance();
        ClienteEndereco model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(ClienteEnderecoInputDTO origin, ClienteEndereco destination) {
        assertEquals(origin.getCep(), destination.getCep());
        assertEquals(origin.getLogradouro(), destination.getLogradouro());
        assertEquals(origin.getNumero(), destination.getNumero());
        assertEquals(origin.getComplemento(), destination.getComplemento());
        assertEquals(origin.getBairro(), destination.getBairro());
        assertEquals(origin.getCidade(), destination.getCidade());
        assertEquals(origin.getEstado(), destination.getEstado());
        assertEquals(origin.getObservacoes(), destination.getObservacoes());
    }
}
