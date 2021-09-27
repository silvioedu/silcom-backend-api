package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.ClienteEnderecoInputAssembler;
import com.silcom.manager.api.assembler.output.ClienteEnderecoOutputAssembler;
import com.silcom.manager.api.dto.input.ClienteEnderecoInputDTO;
import com.silcom.manager.api.dto.output.ClienteEnderecoOutputDTO;
import com.silcom.manager.domain.model.ClienteEndereco;
import com.silcom.manager.domain.service.ClienteEnderecoService;

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
@RequestMapping("/clientes/{clienteId}/enderecos")
public class ClienteEnderecoController {
    
    @Autowired
    private ClienteEnderecoService clienteEnderecoService;

    @Autowired
    private ClienteEnderecoOutputAssembler clienteOutputAssembler;

    @Autowired
    private ClienteEnderecoInputAssembler clienteEnderecoInputAssembler;

    @GetMapping
    public List<ClienteEnderecoOutputDTO> listAll(@PathVariable(required = true) Long clienteId) {
        return clienteOutputAssembler.toColletionDTO(clienteEnderecoService.findAll(clienteId));
    }

    @GetMapping("/{enderecoId}")
    public ClienteEnderecoOutputDTO findById(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long enderecoId) {
        return clienteOutputAssembler.toDTO(clienteEnderecoService.findById(clienteId, enderecoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteEnderecoOutputDTO insert(@PathVariable(required = true) Long clienteId, @RequestBody @Valid ClienteEnderecoInputDTO clienteEnderecoInputDTO) {
        ClienteEndereco clienteEndereco = clienteEnderecoInputAssembler.toModel(clienteEnderecoInputDTO);
        return clienteOutputAssembler.toDTO(clienteEnderecoService.insert(clienteId, clienteEndereco));
    }

    @DeleteMapping("/{enderecoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long enderecoId){
        clienteEnderecoService.delete(clienteId, enderecoId);
    }

    @PutMapping("/{enderecoId}")
    public ClienteEnderecoOutputDTO update(@PathVariable(required = true) Long clienteId,
        @PathVariable(required = true) Long enderecoId, 
        @RequestBody @Valid ClienteEnderecoInputDTO clienteEnderecoInputDTO) {
        ClienteEndereco clienteEndereco = clienteEnderecoInputAssembler.toModel(clienteEnderecoInputDTO);
        return clienteOutputAssembler.toDTO(clienteEnderecoService.update(clienteId, enderecoId, clienteEndereco));
    }
}
