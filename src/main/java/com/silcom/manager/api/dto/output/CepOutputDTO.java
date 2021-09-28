package com.silcom.manager.api.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CepOutputDTO {
    
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

}
