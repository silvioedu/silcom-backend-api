package com.silcom.manager.api.assembler.input;

import com.silcom.manager.api.dto.input.ClienteInputDTO;
import com.silcom.manager.domain.model.Cliente;
import com.silcom.manager.domain.model.Ramo;

import org.springframework.stereotype.Component;

@Component
public class ClienteInputAssembler {
    
    public Cliente toModel(ClienteInputDTO cliente) {
        Ramo ramo = new Ramo();
        ramo.setId(cliente.getRamoId());

        return Cliente.builder()
            .razaoSocial(cliente.getRazaoSocial())
            .nomeFantasia(cliente.getNomeFantasia())
            .ramo(ramo)
            .tipoPessoa(cliente.getTipoPessoa())
            .observacoes(cliente.getObservacoes())
            .build();
    }

}
