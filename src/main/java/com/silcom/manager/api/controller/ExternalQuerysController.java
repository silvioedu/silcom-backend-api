package com.silcom.manager.api.controller;

import com.silcom.manager.api.assembler.output.CepOutputAssembler;
import com.silcom.manager.api.dto.output.CepOutputDTO;
import com.silcom.manager.domain.service.ConsultaCepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/querys")
public class ExternalQuerysController {
    
    @Autowired
    private ConsultaCepService consultaCepService;

    @Autowired
    private CepOutputAssembler cepOutputAssembler;

    @GetMapping("/enderecos/{cepId}")
    public CepOutputDTO findByCep(@PathVariable(required=true) String cepId) {
        return cepOutputAssembler.toDTO(consultaCepService.find(cepId));
    }

}
