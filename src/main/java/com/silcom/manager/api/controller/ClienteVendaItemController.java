package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.ClienteVendaItemInputAssembler;
import com.silcom.manager.api.assembler.output.ClienteVendaItemOutputAssembler;
import com.silcom.manager.api.dto.input.ClienteVendaItemInputDTO;
import com.silcom.manager.api.dto.output.ClienteVendaItemOutputDTO;
import com.silcom.manager.domain.model.ClienteVendaItem;
import com.silcom.manager.domain.service.ClienteVendaItemService;

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
@RequestMapping("/clientes/{clienteId}/vendas/{vendaId}/itens")
public class ClienteVendaItemController {
    
    @Autowired
    private ClienteVendaItemService clienteVendaItemService;

    @Autowired
    private ClienteVendaItemOutputAssembler vendaOutputAssembler;

    @Autowired
    private ClienteVendaItemInputAssembler vendaInputAssembler;

    @GetMapping
    public List<ClienteVendaItemOutputDTO> listAll(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long vendaId) {
        return vendaOutputAssembler.toColletionDTO(clienteVendaItemService.findAll(clienteId, vendaId));
    }

    @GetMapping("/{itemId}")
    public ClienteVendaItemOutputDTO findById(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long vendaId,
        @PathVariable(required = true) Long itemId) {
        return vendaOutputAssembler.toDTO(clienteVendaItemService.findById(clienteId, vendaId, itemId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteVendaItemOutputDTO insert(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long vendaId, 
        @RequestBody @Valid ClienteVendaItemInputDTO vendaItemInputDTO) {
        ClienteVendaItem vendaItem = vendaInputAssembler.toModel(vendaItemInputDTO);
        return vendaOutputAssembler.toDTO(clienteVendaItemService.insert(clienteId, vendaId, vendaItem));
    }

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long vendaId,
        @PathVariable(required = true) Long itemId) {
        clienteVendaItemService.delete(clienteId, vendaId, itemId);
    }

    @PutMapping("/{itemId}")
    public ClienteVendaItemOutputDTO update(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long vendaId, 
        @PathVariable(required = true) Long itemId,    
        @RequestBody @Valid ClienteVendaItemInputDTO vendaItemInputDTO) {
    
        ClienteVendaItem vendaItem = vendaInputAssembler.toModel(vendaItemInputDTO);
        return vendaOutputAssembler.toDTO(clienteVendaItemService.update(clienteId, vendaId, itemId, vendaItem));
    }
}
