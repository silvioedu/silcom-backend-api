package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.VendaInputAssembler;
import com.silcom.manager.api.assembler.output.VendaOutputAssembler;
import com.silcom.manager.api.dto.input.VendaInputDTO;
import com.silcom.manager.api.dto.output.VendaOutputDTO;
import com.silcom.manager.domain.model.Venda;
import com.silcom.manager.domain.service.VendaService;

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
@RequestMapping("/clientes/{clienteId}/vendas")
public class VendaController {
    
    @Autowired
    private VendaService vendaService;

    @Autowired
    private VendaOutputAssembler vendaOutputAssembler;

    @Autowired
    private VendaInputAssembler vendaInputAssembler;

    @GetMapping
    public List<VendaOutputDTO> listAll(@PathVariable(required = true) Long clienteId) {
        return vendaOutputAssembler.toColletionDTO(vendaService.findAll(clienteId));
    }

    @GetMapping("/{vendaId}")
    public VendaOutputDTO findById(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long vendaId) {
        return vendaOutputAssembler.toDTO(vendaService.findById(clienteId, vendaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendaOutputDTO insert(@PathVariable(required = true) Long clienteId, @RequestBody @Valid VendaInputDTO vendaInputDTO) {
        Venda venda = vendaInputAssembler.toModel(vendaInputDTO);
        return vendaOutputAssembler.toDTO(vendaService.insert(clienteId, venda));
    }

    @DeleteMapping("/{vendaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long vendaId){
        vendaService.delete(clienteId, vendaId);
    }

    @PutMapping("/{vendaId}")
    public VendaOutputDTO update(@PathVariable(required = true) Long clienteId,
        @PathVariable(required = true) Long vendaId, 
        @RequestBody @Valid VendaInputDTO vendaInputDTO) {
        Venda venda = vendaInputAssembler.toModel(vendaInputDTO);
        return vendaOutputAssembler.toDTO(vendaService.update(clienteId, vendaId, venda));
    }
}
