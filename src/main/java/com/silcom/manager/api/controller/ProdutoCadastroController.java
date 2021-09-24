package com.silcom.manager.api.controller;

import com.silcom.manager.api.assembler.output.ProdutoComplementoOutputAssembler;
import com.silcom.manager.api.assembler.output.ProdutoCorOutputAssembler;
import com.silcom.manager.api.assembler.output.ProdutoDetalheOutputAssembler;
import com.silcom.manager.api.assembler.output.ProdutoFabricanteOutputAssembler;
import com.silcom.manager.api.assembler.output.ProdutoTipoOutputAssembler;
import com.silcom.manager.api.dto.output.ProdutoCadastrosOutputDTO;
import com.silcom.manager.domain.service.ProdutoComplementoService;
import com.silcom.manager.domain.service.ProdutoCorService;
import com.silcom.manager.domain.service.ProdutoDetalheService;
import com.silcom.manager.domain.service.ProdutoFabricanteService;
import com.silcom.manager.domain.service.ProdutoTipoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastros/produtos/all")
public class ProdutoCadastroController {
    
    @Autowired
    private ProdutoCorService produtoCorService;

    @Autowired
    private ProdutoCorOutputAssembler produtoCorOutputAssembler;

    @Autowired
    private ProdutoComplementoService produtoComplementoService;

    @Autowired
    private ProdutoComplementoOutputAssembler produtoComplementoOutputAssembler;

    @Autowired
    private ProdutoDetalheService produtoDetalheService;

    @Autowired
    private ProdutoDetalheOutputAssembler produtoDetalheOutputAssembler;

    @Autowired
    private ProdutoFabricanteService produtoFabricanteService;

    @Autowired
    private ProdutoFabricanteOutputAssembler produtoFabricanteOutputAssembler;

    @Autowired
    private ProdutoTipoService produtoTipoService;

    @Autowired
    private ProdutoTipoOutputAssembler produtoTipoOutputAssembler;

    @GetMapping
    public ProdutoCadastrosOutputDTO listAll(){
        return ProdutoCadastrosOutputDTO.builder()
            .cores(produtoCorOutputAssembler.toColletionDTO(produtoCorService.findAll()))
            .complementos(produtoComplementoOutputAssembler.toColletionDTO(produtoComplementoService.findAll()))
            .detalhes(produtoDetalheOutputAssembler.toColletionDTO(produtoDetalheService.findAll()))
            .fabricantes(produtoFabricanteOutputAssembler.toColletionDTO(produtoFabricanteService.findAll()))
            .tipos(produtoTipoOutputAssembler.toColletionDTO(produtoTipoService.findAll()))
            .build();
    }
}
