package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.ClienteLembreteInputAssembler;
import com.silcom.manager.api.assembler.output.ClienteLembreteOutputAssembler;
import com.silcom.manager.api.dto.input.ClienteLembreteInputDTO;
import com.silcom.manager.api.dto.output.ClienteLembreteOutputDTO;
import com.silcom.manager.domain.model.ClienteLembrete;
import com.silcom.manager.domain.service.ClienteLembreteService;

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
@RequestMapping("/clientes/{clienteId}/lembretes")
public class ClienteLembreteController {
    
    @Autowired
    private ClienteLembreteService clienteLembreteService;

    @Autowired
    private ClienteLembreteOutputAssembler clienteOutputAssembler;

    @Autowired
    private ClienteLembreteInputAssembler clienteLembreteInputAssembler;

    @GetMapping
    public List<ClienteLembreteOutputDTO> listAll(@PathVariable(required = true) Long clienteId) {
        return clienteOutputAssembler.toColletionDTO(clienteLembreteService.findAll(clienteId));
    }

    @GetMapping("/{lembreteId}")
    public ClienteLembreteOutputDTO findById(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long lembreteId) {
        return clienteOutputAssembler.toDTO(clienteLembreteService.findById(clienteId, lembreteId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteLembreteOutputDTO insert(@PathVariable(required = true) Long clienteId, @RequestBody @Valid ClienteLembreteInputDTO clienteLembreteInputDTO) {
        ClienteLembrete clienteLembrete = clienteLembreteInputAssembler.toModel(clienteLembreteInputDTO);
        return clienteOutputAssembler.toDTO(clienteLembreteService.insert(clienteId, clienteLembrete));
    }

    @DeleteMapping("/{lembreteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long lembreteId){
        clienteLembreteService.delete(clienteId, lembreteId);
    }

    @PutMapping("/{lembreteId}")
    public ClienteLembreteOutputDTO update(@PathVariable(required = true) Long clienteId,
        @PathVariable(required = true) Long lembreteId, 
        @RequestBody @Valid ClienteLembreteInputDTO clienteLembreteInputDTO) {
        ClienteLembrete clienteLembrete = clienteLembreteInputAssembler.toModel(clienteLembreteInputDTO);
        return clienteOutputAssembler.toDTO(clienteLembreteService.update(clienteId, lembreteId, clienteLembrete));
    }
}
