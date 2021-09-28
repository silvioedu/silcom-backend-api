package com.silcom.manager.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cep {
    
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String unidade;
    private String ibge;
    private String gia;

}
