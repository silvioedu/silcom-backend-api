package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.ClienteInputDTO;

import org.apache.commons.lang3.RandomUtils;

public class ClienteMock {
    
    public static Cliente getInstance() {
        Cliente cliente = new Cliente();
        cliente.setId(RandomUtils.nextLong(1, 24));
        cliente.setRazaoSocial("Razao Social " + cliente.getId());
        cliente.setNomeFantasia("Nome Fantasia " + cliente.getId());
        cliente.setRamo(RamoMock.getRealInstance());

        cliente.setTipoPessoa("F");
        cliente.setObservacoes("Observação " + cliente.getId());
        cliente.setDataCriacao(OffsetDateTime.now());
        cliente.setDataAtualizacao(OffsetDateTime.now());
        return cliente;
    }

    public static ClienteInputDTO getInputInstance() {
        Cliente cliente = getInstance();
        ClienteInputDTO input = new ClienteInputDTO();
        input.setRazaoSocial(cliente.getRazaoSocial());
        input.setNomeFantasia(cliente.getNomeFantasia());
        input.setRamoId(cliente.getRamo().getId());
        input.setTipoPessoa(cliente.getTipoPessoa());
        input.setObservacoes(cliente.getObservacoes());
        return input;
    }

}
