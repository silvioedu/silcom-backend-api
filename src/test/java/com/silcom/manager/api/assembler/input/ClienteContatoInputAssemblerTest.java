package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.ClienteContatoInputDTO;
import com.silcom.manager.domain.model.ClienteContato;
import com.silcom.manager.domain.model.ClienteContatoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteContatoInputAssemblerTest {
    
    @Autowired
    private ClienteContatoInputAssembler assembler;

    @Test
    void shouldConvertClienteContatoInput_inClienteContatoModel() {
        ClienteContatoInputDTO input = ClienteContatoMock.getInputInstance();
        ClienteContato model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(ClienteContatoInputDTO origin, ClienteContato destination) {
        assertEquals(origin.getTipoContatoId(), destination.getContatoTipo().getId());
        assertEquals(origin.getContato(), destination.getContato());
        assertEquals(origin.getObservacoes(), destination.getObservacoes());
    }
}
