package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.EnderecoTipoInputAssembler;
import com.silcom.manager.api.assembler.output.EnderecoTipoOutputAssembler;
import com.silcom.manager.api.dto.input.EnderecoTipoInputDTO;
import com.silcom.manager.api.dto.output.EnderecoTipoOutputDTO;
import com.silcom.manager.domain.model.EnderecoTipo;
import com.silcom.manager.domain.service.EnderecoTipoService;

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
@RequestMapping("/cadastros/enderecos-tipos")
public class EnderecoTipoController {
    
    @Autowired
    private EnderecoTipoService documentoTipoService;

    @Autowired
    private EnderecoTipoOutputAssembler documentoTipoOutputAssembler;

    @Autowired
    private EnderecoTipoInputAssembler documentoTipoInputAssembler;

    @GetMapping
    public List<EnderecoTipoOutputDTO> listAll() {
        return documentoTipoOutputAssembler.toColletionDTO(documentoTipoService.findAll());
    }

    @GetMapping("/{id}")
    public EnderecoTipoOutputDTO findById(@PathVariable(required = true) Long id) {
        return documentoTipoOutputAssembler.toDTO(documentoTipoService.findById(id));
    }

    @GetMapping("/por-nome")
    public List<EnderecoTipoOutputDTO> findByNome(String nome) {
        return documentoTipoOutputAssembler.toColletionDTO(documentoTipoService.findByNomeContaining(nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoTipoOutputDTO insert(@RequestBody @Valid EnderecoTipoInputDTO documentoTipoInputDTO) {
        EnderecoTipo documentoTipo = documentoTipoInputAssembler.toModel(documentoTipoInputDTO);
        return documentoTipoOutputAssembler.toDTO(documentoTipoService.insert(documentoTipo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long id){
        documentoTipoService.delete(id);
    }

    @PutMapping("/{id}")
    public EnderecoTipoOutputDTO update(@PathVariable(required = true) Long id, 
        @RequestBody @Valid EnderecoTipoInputDTO documentoTipoInputDTO) {
        EnderecoTipo documentoTipo = documentoTipoInputAssembler.toModel(documentoTipoInputDTO);
        return documentoTipoOutputAssembler.toDTO(documentoTipoService.update(id, documentoTipo));
    }
}
