package com.silcom.manager.api.controller;

import com.silcom.manager.api.dto.output.EmpresaOutputDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
 
    @GetMapping
    public EmpresaOutputDTO getData() {
        return EmpresaOutputDTO.builder()
            .contato("Fone: (11) 2651-1291 ou (11) 96496-7838")
            .email("silcom.calcados@gmail.com")
            .site("www.silcomcalcados.com.br")
            .cnpj("19.550.723/0001-92")
            .inscricaoEstadual("143.158.813.117")
            .build();
    }
}
