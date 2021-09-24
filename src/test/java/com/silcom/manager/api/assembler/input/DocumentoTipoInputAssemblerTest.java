package com.silcom.manager.api.assembler.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.silcom.manager.api.dto.input.DocumentoTipoInputDTO;
import com.silcom.manager.domain.model.DocumentoTipo;
import com.silcom.manager.domain.model.DocumentoTipoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DocumentoTipoInputAssemblerTest {
    
    @Autowired
    private DocumentoTipoInputAssembler assembler;

    @Test
    void shouldConvertDocumentoTipoInput_inDocumentoTipoModel() {
        DocumentoTipoInputDTO input = DocumentoTipoMock.getInputInstance();
        DocumentoTipo model = assembler.toModel(input);
        assertValues(input, model);
    }

    private void assertValues(DocumentoTipoInputDTO origin, DocumentoTipo destination) {
        assertEquals(origin.getNome(), destination.getNome());
    }
}
