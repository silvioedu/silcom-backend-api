package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.ClienteDocumentoInputDTO;
import com.silcom.manager.domain.model.ClienteDocumento;
import com.silcom.manager.domain.model.ClienteDocumentoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteDocumentoInputAssemblerTest {
    
    @Autowired
    private ClienteDocumentoInputAssembler assembler;

    @Test
    void shouldConvertClienteDocumentoInput_inClienteDocumentoModel() {
        ClienteDocumentoInputDTO input = ClienteDocumentoMock.getInputInstance();
        ClienteDocumento model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(ClienteDocumentoInputDTO origin, ClienteDocumento destination) {
        assertEquals(origin.getTipoDocumentoId(), destination.getDocumentoTipo().getId());
        assertEquals(origin.getDocumento(), destination.getDocumento());
        assertEquals(origin.getObservacoes(), destination.getObservacoes());
    }
}
