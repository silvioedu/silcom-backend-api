package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.DocumentoTipoInputAssembler;
import com.silcom.manager.api.assembler.output.DocumentoTipoOutputAssembler;
import com.silcom.manager.api.dto.input.DocumentoTipoInputDTO;
import com.silcom.manager.api.dto.output.DocumentoTipoOutputDTO;
import com.silcom.manager.domain.service.DocumentoTipoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastros/documentos-tipos")
public class DocumentoTipoController {
    
    @Autowired
    private DocumentoTipoService documentoTipoService;

    @Autowired
    private DocumentoTipoOutputAssembler documentoTipoOutputAssembler;

    @Autowired
    private DocumentoTipoInputAssembler documentoTipoInputAssembler;

    @GetMapping
    public List<DocumentoTipoOutputDTO> listAll() {
        return documentoTipoOutputAssembler.toColletionDTO(documentoTipoService.findAll());
    }

    @GetMapping("/{id}")
    public DocumentoTipoOutputDTO findById(@PathVariable(required = true) Long id) {
        return documentoTipoOutputAssembler.toDTO(documentoTipoService.findById(id));
    }

    @GetMapping("/por-nome")
    public List<DocumentoTipoOutputDTO> findByNome(String nome) {
        return documentoTipoOutputAssembler.toColletionDTO(documentoTipoService.findByNomeContaining(nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentoTipoOutputDTO insert(@RequestBody @Valid DocumentoTipoInputDTO documentoTipoInputDTO) {
        var documentoTipo = documentoTipoInputAssembler.toModel(documentoTipoInputDTO);
        return documentoTipoOutputAssembler.toDTO(documentoTipoService.insert(documentoTipo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long id){
        documentoTipoService.delete(id);
    }

    @PutMapping("/{id}")
    public DocumentoTipoOutputDTO update(@PathVariable(required = true) Long id, 
        @RequestBody @Valid DocumentoTipoInputDTO documentoTipoInputDTO) {
        var documentoTipo = documentoTipoInputAssembler.toModel(documentoTipoInputDTO);
        return documentoTipoOutputAssembler.toDTO(documentoTipoService.update(id, documentoTipo));
    }
}
