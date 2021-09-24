package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.ProdutoTipoInputAssembler;
import com.silcom.manager.api.assembler.output.ProdutoTipoOutputAssembler;
import com.silcom.manager.api.dto.input.ProdutoTipoInputDTO;
import com.silcom.manager.api.dto.output.ProdutoTipoOutputDTO;
import com.silcom.manager.domain.service.ProdutoTipoService;

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
@RequestMapping("/cadastros/produtos/tipos")
public class ProdutoTipoController {
    
    @Autowired
    private ProdutoTipoService produtoTipoService;

    @Autowired
    private ProdutoTipoOutputAssembler produtoTipoOutputAssembler;

    @Autowired
    private ProdutoTipoInputAssembler produtoTipoInputAssembler;

    @GetMapping
    public List<ProdutoTipoOutputDTO> listAll() {
        return produtoTipoOutputAssembler.toColletionDTO(produtoTipoService.findAll());
    }

    @GetMapping("/{id}")
    public ProdutoTipoOutputDTO findById(@PathVariable(required = true) Long id) {
        return produtoTipoOutputAssembler.toDTO(produtoTipoService.findById(id));
    }

    @GetMapping("/por-nome")
    public List<ProdutoTipoOutputDTO> findByNome(String nome) {
        return produtoTipoOutputAssembler.toColletionDTO(produtoTipoService.findByNomeContaining(nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoTipoOutputDTO insert(@RequestBody @Valid ProdutoTipoInputDTO produtoTipoInputDTO) {
        var produtoTipo = produtoTipoInputAssembler.toModel(produtoTipoInputDTO);
        return produtoTipoOutputAssembler.toDTO(produtoTipoService.insert(produtoTipo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long id){
        produtoTipoService.delete(id);
    }

    @PutMapping("/{id}")
    public ProdutoTipoOutputDTO update(@PathVariable(required = true) Long id, 
        @RequestBody @Valid ProdutoTipoInputDTO produtoTipoInputDTO) {
        var produtoTipo = produtoTipoInputAssembler.toModel(produtoTipoInputDTO);
        return produtoTipoOutputAssembler.toDTO(produtoTipoService.update(id, produtoTipo));
    }
}
