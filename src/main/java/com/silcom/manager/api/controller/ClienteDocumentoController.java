package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.ClienteDocumentoInputAssembler;
import com.silcom.manager.api.assembler.output.ClienteDocumentoOutputAssembler;
import com.silcom.manager.api.dto.input.ClienteDocumentoInputDTO;
import com.silcom.manager.api.dto.output.ClienteDocumentoOutputDTO;
import com.silcom.manager.domain.model.ClienteDocumento;
import com.silcom.manager.domain.service.ClienteDocumentoService;

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
@RequestMapping("/clientes/{clienteId}/documentos")
public class ClienteDocumentoController {
    
    @Autowired
    private ClienteDocumentoService clienteDocumentoService;

    @Autowired
    private ClienteDocumentoOutputAssembler clienteOutputAssembler;

    @Autowired
    private ClienteDocumentoInputAssembler clienteDocumentoInputAssembler;

    @GetMapping
    public List<ClienteDocumentoOutputDTO> listAll(@PathVariable(required = true) Long clienteId) {
        return clienteOutputAssembler.toColletionDTO(clienteDocumentoService.findAll(clienteId));
    }

    @GetMapping("/{documentoId}")
    public ClienteDocumentoOutputDTO findById(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long documentoId) {
        return clienteOutputAssembler.toDTO(clienteDocumentoService.findById(clienteId, documentoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDocumentoOutputDTO insert(@PathVariable(required = true) Long clienteId, @RequestBody @Valid ClienteDocumentoInputDTO clienteDocumentoInputDTO) {
        ClienteDocumento clienteDocumento = clienteDocumentoInputAssembler.toModel(clienteDocumentoInputDTO);
        return clienteOutputAssembler.toDTO(clienteDocumentoService.insert(clienteId, clienteDocumento));
    }

    @DeleteMapping("/{documentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long clienteId, @PathVariable(required = true) Long documentoId){
        clienteDocumentoService.delete(clienteId, documentoId);
    }

    @PutMapping("/{documentoId}")
    public ClienteDocumentoOutputDTO update(@PathVariable(required = true) Long clienteId,
        @PathVariable(required = true) Long documentoId, 
        @RequestBody @Valid ClienteDocumentoInputDTO clienteDocumentoInputDTO) {
        ClienteDocumento clienteDocumento = clienteDocumentoInputAssembler.toModel(clienteDocumentoInputDTO);
        return clienteOutputAssembler.toDTO(clienteDocumentoService.update(clienteId, documentoId, clienteDocumento));
    }
}
