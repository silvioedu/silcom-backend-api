package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.ClienteInputDTO;
import com.silcom.manager.domain.model.Cliente;
import com.silcom.manager.domain.model.ClienteMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteInputAssemblerTest {
    
    @Autowired
    private ClienteInputAssembler assembler;

    @Test
    void shouldConvertClienteInput_inClienteModel() {
        ClienteInputDTO input = ClienteMock.getInputInstance();
        Cliente model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(ClienteInputDTO origin, Cliente destination) {
        assertEquals(origin.getRazaoSocial(), destination.getRazaoSocial());
        assertEquals(origin.getNomeFantasia(), destination.getNomeFantasia());
        assertEquals(origin.getRamoId(), destination.getRamo().getId());
        assertEquals(origin.getTipoPessoa(), destination.getTipoPessoa());
        assertEquals(origin.getObservacoes(), destination.getObservacoes());
    }
}
