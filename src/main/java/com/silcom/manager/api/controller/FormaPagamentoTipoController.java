package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.FormaPagamentoTipoInputAssembler;
import com.silcom.manager.api.assembler.output.FormaPagamentoTipoOutputAssembler;
import com.silcom.manager.api.dto.input.FormaPagamentoTipoInputDTO;
import com.silcom.manager.api.dto.output.FormaPagamentoTipoOutputDTO;
import com.silcom.manager.domain.model.FormaPagamentoTipo;
import com.silcom.manager.domain.service.FormaPagamentoTipoService;

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
@RequestMapping("/cadastros/formas-pagamentos-tipos")
public class FormaPagamentoTipoController {
    
    @Autowired
    private FormaPagamentoTipoService formaPagamentoTipoService;

    @Autowired
    private FormaPagamentoTipoOutputAssembler formaPagamentoTipoOutputAssembler;

    @Autowired
    private FormaPagamentoTipoInputAssembler formaPagamentoTipoInputAssembler;

    @GetMapping
    public List<FormaPagamentoTipoOutputDTO> listAll() {
        return formaPagamentoTipoOutputAssembler.toColletionDTO(formaPagamentoTipoService.findAll());
    }

    @GetMapping("/{id}")
    public FormaPagamentoTipoOutputDTO findById(@PathVariable(required = true) Long id) {
        return formaPagamentoTipoOutputAssembler.toDTO(formaPagamentoTipoService.findById(id));
    }

    @GetMapping("/por-nome")
    public List<FormaPagamentoTipoOutputDTO> findByNome(String nome) {
        return formaPagamentoTipoOutputAssembler.toColletionDTO(formaPagamentoTipoService.findByNomeContaining(nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoTipoOutputDTO insert(@RequestBody @Valid FormaPagamentoTipoInputDTO formaPagamentoTipoInputDTO) {
        FormaPagamentoTipo formaPagamentoTipo = formaPagamentoTipoInputAssembler.toModel(formaPagamentoTipoInputDTO);
        return formaPagamentoTipoOutputAssembler.toDTO(formaPagamentoTipoService.insert(formaPagamentoTipo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long id){
        formaPagamentoTipoService.delete(id);
    }

    @PutMapping("/{id}")
    public FormaPagamentoTipoOutputDTO update(@PathVariable(required = true) Long id, 
        @RequestBody @Valid FormaPagamentoTipoInputDTO formaPagamentoTipoInputDTO) {
        FormaPagamentoTipo formaPagamentoTipo = formaPagamentoTipoInputAssembler.toModel(formaPagamentoTipoInputDTO);
        return formaPagamentoTipoOutputAssembler.toDTO(formaPagamentoTipoService.update(id, formaPagamentoTipo));
    }
}
