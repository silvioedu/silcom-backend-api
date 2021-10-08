package com.silcom.manager.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.silcom.manager.api.assembler.input.LembreteTipoInputAssembler;
import com.silcom.manager.api.assembler.output.LembreteTipoOutputAssembler;
import com.silcom.manager.api.dto.input.LembreteTipoInputDTO;
import com.silcom.manager.api.dto.output.LembreteTipoOutputDTO;
import com.silcom.manager.domain.model.LembreteTipo;
import com.silcom.manager.domain.service.LembreteTipoService;

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
@RequestMapping("/cadastros/lembretes-tipos")
public class LembreteTipoController {
    
    @Autowired
    private LembreteTipoService lembreteTipoService;

    @Autowired
    private LembreteTipoOutputAssembler lembreteTipoOutputAssembler;

    @Autowired
    private LembreteTipoInputAssembler lembreteTipoInputAssembler;

    @GetMapping
    public List<LembreteTipoOutputDTO> listAll() {
        return lembreteTipoOutputAssembler.toColletionDTO(lembreteTipoService.findAll());
    }

    @GetMapping("/{id}")
    public LembreteTipoOutputDTO findById(@PathVariable(required = true) Long id) {
        return lembreteTipoOutputAssembler.toDTO(lembreteTipoService.findById(id));
    }

    @GetMapping("/por-nome")
    public List<LembreteTipoOutputDTO> findByNome(String nome) {
        return lembreteTipoOutputAssembler.toColletionDTO(lembreteTipoService.findByNomeContaining(nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LembreteTipoOutputDTO insert(@RequestBody @Valid LembreteTipoInputDTO lembreteTipoInputDTO) {
        LembreteTipo lembreteTipo = lembreteTipoInputAssembler.toModel(lembreteTipoInputDTO);
        return lembreteTipoOutputAssembler.toDTO(lembreteTipoService.insert(lembreteTipo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long id){
        lembreteTipoService.delete(id);
    }

    @PutMapping("/{id}")
    public LembreteTipoOutputDTO update(@PathVariable(required = true) Long id, 
        @RequestBody @Valid LembreteTipoInputDTO lembreteTipoInputDTO) {
        LembreteTipo lembreteTipo = lembreteTipoInputAssembler.toModel(lembreteTipoInputDTO);
        return lembreteTipoOutputAssembler.toDTO(lembreteTipoService.update(id, lembreteTipo));
    }
}
