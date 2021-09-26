package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.ClienteDocumentoOutputDTO;
import com.silcom.manager.domain.model.ClienteDocumento;
import com.silcom.manager.domain.model.ClienteDocumentoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteDocumentoOutputAssemblerTest {
    
    @Autowired
    private ClienteDocumentoOutputAssembler assembler;

    @Test
    void shouldConvertClienteDocumentoModel_inClienteDocumentoOutput() {
        ClienteDocumento model = ClienteDocumentoMock.getInstance();
        ClienteDocumentoOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListClienteDocumentoModel_inListClienteDocumentoOutput() {
        int max = 10;
        List<ClienteDocumento> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            ClienteDocumento model = ClienteDocumentoMock.getInstance();
            modelList.add(model);
        }

        List<ClienteDocumentoOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(ClienteDocumento origin, ClienteDocumentoOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getDocumentoTipo().getNome(), destination.getTipoDocumentoNome());
        assertEquals(origin.getDocumento(), destination.getDocumento());
        assertEquals(origin.getObservacoes(), destination.getObservacoes());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
