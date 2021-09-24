package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.ProdutoCorInputAssembler;
import com.silcom.manager.api.assembler.output.ProdutoCorOutputAssembler;
import com.silcom.manager.api.dto.input.ProdutoCorInputDTO;
import com.silcom.manager.api.dto.output.ProdutoCorOutputDTO;
import com.silcom.manager.domain.service.ProdutoCorService;

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
@RequestMapping("/cadastros/produtos/cores")
public class ProdutoCorController {
    
    @Autowired
    private ProdutoCorService produtoCorService;

    @Autowired
    private ProdutoCorOutputAssembler produtoCorOutputAssembler;

    @Autowired
    private ProdutoCorInputAssembler produtoCorInputAssembler;

    @GetMapping
    public List<ProdutoCorOutputDTO> listAll() {
        return produtoCorOutputAssembler.toColletionDTO(produtoCorService.findAll());
    }

    @GetMapping("/{id}")
    public ProdutoCorOutputDTO findById(@PathVariable(required = true) Long id) {
        return produtoCorOutputAssembler.toDTO(produtoCorService.findById(id));
    }

    @GetMapping("/por-nome")
    public List<ProdutoCorOutputDTO> findByNome(String nome) {
        return produtoCorOutputAssembler.toColletionDTO(produtoCorService.findByNomeContaining(nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoCorOutputDTO insert(@RequestBody @Valid ProdutoCorInputDTO produtoCorInputDTO) {
        var produtoCor = produtoCorInputAssembler.toModel(produtoCorInputDTO);
        return produtoCorOutputAssembler.toDTO(produtoCorService.insert(produtoCor));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long id){
        produtoCorService.delete(id);
    }

    @PutMapping("/{id}")
    public ProdutoCorOutputDTO update(@PathVariable(required = true) Long id, 
        @RequestBody @Valid ProdutoCorInputDTO produtoCorInputDTO) {
        var produtoCor = produtoCorInputAssembler.toModel(produtoCorInputDTO);
        return produtoCorOutputAssembler.toDTO(produtoCorService.update(id, produtoCor));
    }
}
