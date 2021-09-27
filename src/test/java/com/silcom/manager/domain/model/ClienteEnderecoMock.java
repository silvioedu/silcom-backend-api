package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.ClienteEnderecoInputDTO;

import org.apache.commons.lang3.RandomUtils;

public class ClienteEnderecoMock {
    
    public static ClienteEndereco getInstance() {
        ClienteEndereco clienteEndereco = new ClienteEndereco();
        clienteEndereco.setId(RandomUtils.nextLong(1, 24));
        clienteEndereco.setCep("89095-392");
        clienteEndereco.setLogradouro("Rua Frederico Bauer");
        clienteEndereco.setNumero("859");
        clienteEndereco.setComplemento("apto 3");
        clienteEndereco.setBairro("Vila Itoupava");
        clienteEndereco.setCidade("Blumenau");
        clienteEndereco.setEstado("SC");
        clienteEndereco.setObservacoes("Observação " + clienteEndereco.getId());
        clienteEndereco.setDataCriacao(OffsetDateTime.now());
        clienteEndereco.setDataAtualizacao(OffsetDateTime.now());
        return clienteEndereco;
    }

    public static ClienteEnderecoInputDTO getInputInstance() {
        ClienteEndereco clienteEndereco = getInstance();
        ClienteEnderecoInputDTO input = new ClienteEnderecoInputDTO();
        input.setCep(clienteEndereco.getCep());
        input.setLogradouro(clienteEndereco.getLogradouro());
        input.setNumero(clienteEndereco.getNumero());
        input.setComplemento(clienteEndereco.getComplemento());
        input.setBairro(clienteEndereco.getBairro());
        input.setCidade(clienteEndereco.getCidade());
        input.setEstado(clienteEndereco.getEstado());
        input.setObservacoes(clienteEndereco.getObservacoes());
        return input;
    }

}
