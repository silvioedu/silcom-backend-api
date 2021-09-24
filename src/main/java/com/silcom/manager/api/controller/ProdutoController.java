package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.ProdutoInputAssembler;
import com.silcom.manager.api.assembler.output.ProdutoIdOutputAssembler;
import com.silcom.manager.api.assembler.output.ProdutoOutputAssembler;
import com.silcom.manager.api.dto.input.ProdutoInputDTO;
import com.silcom.manager.api.dto.output.ProdutoIdOutputDTO;
import com.silcom.manager.api.dto.output.ProdutoOutputDTO;
import com.silcom.manager.domain.service.ProdutoService;

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
@RequestMapping("/cadastros/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoOutputAssembler produtoOutputAssembler;

    @Autowired
    private ProdutoInputAssembler produtoInputAssembler;

    @Autowired
    private ProdutoIdOutputAssembler produtoIdOutputAssembler;

    @GetMapping
    public List<ProdutoOutputDTO> listAll() {
        return produtoOutputAssembler.toColletionDTO(produtoService.findAll());
    }

    @GetMapping("/{id}")
    public ProdutoOutputDTO findById(@PathVariable(required = true) Long id) {
        return produtoOutputAssembler.toDTO(produtoService.findById(id));
    }

    @GetMapping("/{id}/id")
    public ProdutoIdOutputDTO findIdById(@PathVariable(required = true) Long id) {
        return produtoIdOutputAssembler.toDTO(produtoService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoOutputDTO insert(@RequestBody @Valid ProdutoInputDTO produtoInputDTO) {
        var produto = produtoInputAssembler.toModel(produtoInputDTO);
        System.out.println(produtoInputDTO);
        return produtoOutputAssembler.toDTO(produtoService.insert(produto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long id){
        produtoService.delete(id);
    }

    @PutMapping("/{id}")
    public ProdutoOutputDTO update(@PathVariable(required = true) Long id, 
        @RequestBody @Valid ProdutoInputDTO produtoInputDTO) {
        var produto = produtoInputAssembler.toModel(produtoInputDTO);
        return produtoOutputAssembler.toDTO(produtoService.update(id, produto));
    }

}
