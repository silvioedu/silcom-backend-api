package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.ClienteContatoInputAssembler;
import com.silcom.manager.api.assembler.output.ClienteContatoOutputAssembler;
import com.silcom.manager.api.dto.input.ClienteContatoInputDTO;
import com.silcom.manager.api.dto.output.ClienteContatoOutputDTO;
import com.silcom.manager.domain.model.ClienteContato;
import com.silcom.manager.domain.service.ClienteContatoService;

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
@RequestMapping("/clientes/{clienteId}/contatos")
public class ClienteContatoController {
    
    @Autowired
    private ClienteContatoService clienteContatoService;

    @Autowired
    private ClienteContatoOutputAssembler clienteOutputAssembler;

    @Autowired
    private ClienteContatoInputAssembler clienteContatoInputAssembler;

    @GetMapping
    public List<ClienteContatoOutputDTO> listAll(@PathVariable(required = true) Long clienteId) {
        return clienteOutputAssembler.toColletionDTO(clienteContatoService.findAll(clienteId));
    }

    @GetMapping("/{contatoId}")
    public ClienteContatoOutputDTO findById(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long contatoId) {
        return clienteOutputAssembler.toDTO(clienteContatoService.findById(clienteId, contatoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteContatoOutputDTO insert(@PathVariable(required = true) Long clienteId, @RequestBody @Valid ClienteContatoInputDTO clienteContatoInputDTO) {
        ClienteContato clienteContato = clienteContatoInputAssembler.toModel(clienteContatoInputDTO);
        return clienteOutputAssembler.toDTO(clienteContatoService.insert(clienteId, clienteContato));
    }

    @DeleteMapping("/{contatoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long contatoId){
        clienteContatoService.delete(clienteId, contatoId);
    }

    @PutMapping("/{contatoId}")
    public ClienteContatoOutputDTO update(@PathVariable(required = true) Long clienteId,
        @PathVariable(required = true) Long contatoId, 
        @RequestBody @Valid ClienteContatoInputDTO clienteContatoInputDTO) {
        ClienteContato clienteContato = clienteContatoInputAssembler.toModel(clienteContatoInputDTO);
        return clienteOutputAssembler.toDTO(clienteContatoService.update(clienteId, contatoId, clienteContato));
    }
}
