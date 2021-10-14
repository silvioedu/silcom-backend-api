package com.silcom.manager.api.dto.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmpresaOutputDTO {
    
    private String contato;
    private String email;
    private String site;
    private String cnpj;
    private String inscricaoEstadual;
}
