package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.ClienteVendaInputAssembler;
import com.silcom.manager.api.assembler.output.ClienteVendaOutputAssembler;
import com.silcom.manager.api.dto.input.ClienteVendaInputDTO;
import com.silcom.manager.api.dto.output.ClienteVendaOutputDTO;
import com.silcom.manager.domain.model.ClienteVenda;
import com.silcom.manager.domain.model.VendaStatus;
import com.silcom.manager.domain.service.ClienteVendaService;

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
public class ClienteVendaController {
    
    @Autowired
    private ClienteVendaService vendaService;

    @Autowired
    private ClienteVendaOutputAssembler vendaOutputAssembler;

    @Autowired
    private ClienteVendaInputAssembler vendaInputAssembler;

    @GetMapping
    public List<ClienteVendaOutputDTO> listAll(@PathVariable(required = true) Long clienteId) {
        return vendaOutputAssembler.toColletionDTO(vendaService.findAll(clienteId));
    }

    @GetMapping("/{vendaId}")
    public ClienteVendaOutputDTO findById(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long vendaId) {
        return vendaOutputAssembler.toDTO(vendaService.findById(clienteId, vendaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteVendaOutputDTO insert(@PathVariable(required = true) Long clienteId, @RequestBody @Valid ClienteVendaInputDTO vendaInputDTO) {
        ClienteVenda venda = vendaInputAssembler.toModel(vendaInputDTO);
        return vendaOutputAssembler.toDTO(vendaService.insert(clienteId, venda));
    }

    @DeleteMapping("/{vendaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long vendaId){
        vendaService.delete(clienteId, vendaId);
    }

    @PutMapping("/{vendaId}")
    public ClienteVendaOutputDTO update(@PathVariable(required = true) Long clienteId,
        @PathVariable(required = true) Long vendaId, 
        @RequestBody @Valid ClienteVendaInputDTO vendaInputDTO) {
        ClienteVenda venda = vendaInputAssembler.toModel(vendaInputDTO);
        return vendaOutputAssembler.toDTO(vendaService.update(clienteId, vendaId, venda));
    }

    @PutMapping("/{vendaId}/update-status")
    public ClienteVendaOutputDTO updateStatus(@PathVariable(required = true) Long clienteId,
        @PathVariable(required = true) Long vendaId, VendaStatus status) {
            return vendaOutputAssembler.toDTO(vendaService.updateStatus(clienteId, vendaId, status));
    }

}
