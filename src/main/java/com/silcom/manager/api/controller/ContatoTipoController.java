package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.ContatoTipoInputAssembler;
import com.silcom.manager.api.assembler.output.ContatoTipoOutputAssembler;
import com.silcom.manager.api.dto.input.ContatoTipoInputDTO;
import com.silcom.manager.api.dto.output.ContatoTipoOutputDTO;
import com.silcom.manager.domain.service.ContatoTipoService;

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
@RequestMapping("/cadastros/contatos-tipos")
public class ContatoTipoController {
    
    @Autowired
    private ContatoTipoService contatoTipoService;

    @Autowired
    private ContatoTipoOutputAssembler contatoTipoOutputAssembler;

    @Autowired
    private ContatoTipoInputAssembler contatoTipoInputAssembler;

    @GetMapping
    public List<ContatoTipoOutputDTO> listAll() {
        return contatoTipoOutputAssembler.toColletionDTO(contatoTipoService.findAll());
    }

    @GetMapping("/{id}")
    public ContatoTipoOutputDTO findById(@PathVariable(required = true) Long id) {
        return contatoTipoOutputAssembler.toDTO(contatoTipoService.findById(id));
    }

    @GetMapping("/por-nome")
    public List<ContatoTipoOutputDTO> findByNome(String nome) {
        return contatoTipoOutputAssembler.toColletionDTO(contatoTipoService.findByNomeContaining(nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoTipoOutputDTO insert(@RequestBody @Valid ContatoTipoInputDTO contatoTipoInputDTO) {
        var contatoTipo = contatoTipoInputAssembler.toModel(contatoTipoInputDTO);
        return contatoTipoOutputAssembler.toDTO(contatoTipoService.insert(contatoTipo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long id){
        contatoTipoService.delete(id);
    }

    @PutMapping("/{id}")
    public ContatoTipoOutputDTO update(@PathVariable(required = true) Long id, 
        @RequestBody @Valid ContatoTipoInputDTO contatoTipoInputDTO) {
        var contatoTipo = contatoTipoInputAssembler.toModel(contatoTipoInputDTO);
        return contatoTipoOutputAssembler.toDTO(contatoTipoService.update(id, contatoTipo));
    }
}
