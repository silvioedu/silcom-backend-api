package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.ClienteInputAssembler;
import com.silcom.manager.api.assembler.output.ClienteOutputAssembler;
import com.silcom.manager.api.dto.input.ClienteInputDTO;
import com.silcom.manager.api.dto.output.ClienteOutputDTO;
import com.silcom.manager.domain.model.Cliente;
import com.silcom.manager.domain.service.ClienteService;

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
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteOutputAssembler clienteOutputAssembler;

    @Autowired
    private ClienteInputAssembler clienteInputAssembler;

    @GetMapping
    public List<ClienteOutputDTO> listAll() {
        return clienteOutputAssembler.toColletionDTO(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ClienteOutputDTO findById(@PathVariable(required = true) Long id) {
        return clienteOutputAssembler.toDTO(clienteService.findById(id));
    }

    @GetMapping("/por-razao-social")
    public List<ClienteOutputDTO> findByRazaoSocial(String razaoSocial) {
        return clienteOutputAssembler.toColletionDTO(clienteService.findByRazaoSocialContaining(razaoSocial));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteOutputDTO insert(@RequestBody @Valid ClienteInputDTO clienteInputDTO) {
        Cliente cliente = clienteInputAssembler.toModel(clienteInputDTO);
        return clienteOutputAssembler.toDTO(clienteService.insert(cliente));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long id){
        clienteService.delete(id);
    }

    @PutMapping("/{id}")
    public ClienteOutputDTO update(@PathVariable(required = true) Long id, 
        @RequestBody @Valid ClienteInputDTO clienteInputDTO) {
        Cliente cliente = clienteInputAssembler.toModel(clienteInputDTO);
        return clienteOutputAssembler.toDTO(clienteService.update(id, cliente));
    }
}
