package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.ProdutoFabricanteInputAssembler;
import com.silcom.manager.api.assembler.output.ProdutoFabricanteOutputAssembler;
import com.silcom.manager.api.dto.input.ProdutoFabricanteInputDTO;
import com.silcom.manager.api.dto.output.ProdutoFabricanteOutputDTO;
import com.silcom.manager.domain.service.ProdutoFabricanteService;

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
@RequestMapping("/cadastros/produtos/fabricantes")
public class ProdutoFabricanteController {
    
    @Autowired
    private ProdutoFabricanteService produtoFabricanteService;

    @Autowired
    private ProdutoFabricanteOutputAssembler produtoFabricanteOutputAssembler;

    @Autowired
    private ProdutoFabricanteInputAssembler produtoFabricanteInputAssembler;

    @GetMapping
    public List<ProdutoFabricanteOutputDTO> listAll() {
        return produtoFabricanteOutputAssembler.toColletionDTO(produtoFabricanteService.findAll());
    }

    @GetMapping("/{id}")
    public ProdutoFabricanteOutputDTO findById(@PathVariable(required = true) Long id) {
        return produtoFabricanteOutputAssembler.toDTO(produtoFabricanteService.findById(id));
    }

    @GetMapping("/por-nome")
    public List<ProdutoFabricanteOutputDTO> findByNome(String nome) {
        return produtoFabricanteOutputAssembler.toColletionDTO(produtoFabricanteService.findByNomeContaining(nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoFabricanteOutputDTO insert(@RequestBody @Valid ProdutoFabricanteInputDTO produtoFabricanteInputDTO) {
        var produtoFabricante = produtoFabricanteInputAssembler.toModel(produtoFabricanteInputDTO);
        return produtoFabricanteOutputAssembler.toDTO(produtoFabricanteService.insert(produtoFabricante));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long id){
        produtoFabricanteService.delete(id);
    }

    @PutMapping("/{id}")
    public ProdutoFabricanteOutputDTO update(@PathVariable(required = true) Long id, 
        @RequestBody @Valid ProdutoFabricanteInputDTO produtoFabricanteInputDTO) {
        var produtoFabricante = produtoFabricanteInputAssembler.toModel(produtoFabricanteInputDTO);
        return produtoFabricanteOutputAssembler.toDTO(produtoFabricanteService.update(id, produtoFabricante));
    }
}
