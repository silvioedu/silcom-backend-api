package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.RamoInputAssembler;
import com.silcom.manager.api.assembler.output.RamoOutputAssembler;
import com.silcom.manager.api.dto.input.RamoInputDTO;
import com.silcom.manager.api.dto.output.RamoOutputDTO;
import com.silcom.manager.domain.service.RamoService;

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
@RequestMapping("/cadastros/ramos")
public class RamoController {
    
    @Autowired
    private RamoService ramoService;

    @Autowired
    private RamoOutputAssembler ramoOutputAssembler;

    @Autowired
    private RamoInputAssembler ramoInputAssembler;

    @GetMapping
    public List<RamoOutputDTO> listAll() {
        return ramoOutputAssembler.toColletionDTO(ramoService.findAll());
    }

    @GetMapping("/{id}")
    public RamoOutputDTO findById(@PathVariable(required = true) Long id) {
        return ramoOutputAssembler.toDTO(ramoService.findById(id));
    }

    @GetMapping("/por-nome")
    public List<RamoOutputDTO> findByNome(String nome) {
        return ramoOutputAssembler.toColletionDTO(ramoService.findByNomeContaining(nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RamoOutputDTO insert(@RequestBody @Valid RamoInputDTO ramoInputDTO) {
        var ramo = ramoInputAssembler.toModel(ramoInputDTO);
        return ramoOutputAssembler.toDTO(ramoService.insert(ramo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long id){
        ramoService.delete(id);
    }

    @PutMapping("/{id}")
    public RamoOutputDTO update(@PathVariable(required = true) Long id, 
        @RequestBody @Valid RamoInputDTO ramoInputDTO) {
        var ramo = ramoInputAssembler.toModel(ramoInputDTO);
        return ramoOutputAssembler.toDTO(ramoService.update(id, ramo));
    }
}
