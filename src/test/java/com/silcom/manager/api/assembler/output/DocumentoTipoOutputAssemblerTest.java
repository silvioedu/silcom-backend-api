package com.silcom.manager.api.assembler.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.api.dto.output.DocumentoTipoOutputDTO;
import com.silcom.manager.domain.model.DocumentoTipo;
import com.silcom.manager.domain.model.DocumentoTipoMock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DocumentoTipoOutputAssemblerTest {
    
    @Autowired
    private DocumentoTipoOutputAssembler assembler;

    @Test
    void shouldConvertDocumentoTipoModel_inDocumentoTipoOutput() {
        DocumentoTipo model = DocumentoTipoMock.getInstance();
        DocumentoTipoOutputDTO output = assembler.toDTO(model);
        assertValues(model, output);
    }

    @Test
    void shouldConvertListDocumentoTipoModel_inListDocumentoTipoOutput() {
        int max = 10;
        List<DocumentoTipo> modelList = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            DocumentoTipo model = DocumentoTipoMock.getInstance();
            modelList.add(model);
        }

        List<DocumentoTipoOutputDTO> outputList = assembler.toColletionDTO(modelList);

        for(int i = 0; i < max; i++) {
            assertValues(modelList.get(i), outputList.get(i));
        }
    }

    private void assertValues(DocumentoTipo origin, DocumentoTipoOutputDTO destination) {
        assertEquals(origin.getId(), destination.getId());
        assertEquals(origin.getNome(), destination.getNome());
        assertEquals(origin.getDataCriacao(), destination.getDataCriacao());
        assertEquals(origin.getDataAtualizacao(), destination.getDataAtualizacao());
    }
}
