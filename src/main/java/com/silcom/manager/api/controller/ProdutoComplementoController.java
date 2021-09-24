package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.ProdutoComplementoInputAssembler;
import com.silcom.manager.api.assembler.output.ProdutoComplementoOutputAssembler;
import com.silcom.manager.api.dto.input.ProdutoComplementoInputDTO;
import com.silcom.manager.api.dto.output.ProdutoComplementoOutputDTO;
import com.silcom.manager.domain.service.ProdutoComplementoService;

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
@RequestMapping("/cadastros/produtos/complementos")
public class ProdutoComplementoController {
    
    @Autowired
    private ProdutoComplementoService produtoComplementoService;

    @Autowired
    private ProdutoComplementoOutputAssembler produtoComplementoOutputAssembler;

    @Autowired
    private ProdutoComplementoInputAssembler produtoComplementoInputAssembler;

    @GetMapping
    public List<ProdutoComplementoOutputDTO> listAll() {
        return produtoComplementoOutputAssembler.toColletionDTO(produtoComplementoService.findAll());
    }

    @GetMapping("/{id}")
    public ProdutoComplementoOutputDTO findById(@PathVariable(required = true) Long id) {
        return produtoComplementoOutputAssembler.toDTO(produtoComplementoService.findById(id));
    }

    @GetMapping("/por-nome")
    public List<ProdutoComplementoOutputDTO> findByNome(String nome) {
        return produtoComplementoOutputAssembler.toColletionDTO(produtoComplementoService.findByNomeContaining(nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoComplementoOutputDTO insert(@RequestBody @Valid ProdutoComplementoInputDTO produtoComplementoInputDTO) {
        var produtoComplemento = produtoComplementoInputAssembler.toModel(produtoComplementoInputDTO);
        return produtoComplementoOutputAssembler.toDTO(produtoComplementoService.insert(produtoComplemento));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long id){
        produtoComplementoService.delete(id);
    }

    @PutMapping("/{id}")
    public ProdutoComplementoOutputDTO update(@PathVariable(required = true) Long id, 
        @RequestBody @Valid ProdutoComplementoInputDTO produtoComplementoInputDTO) {
        var produtoComplemento = produtoComplementoInputAssembler.toModel(produtoComplementoInputDTO);
        return produtoComplementoOutputAssembler.toDTO(produtoComplementoService.update(id, produtoComplemento));
    }
}
