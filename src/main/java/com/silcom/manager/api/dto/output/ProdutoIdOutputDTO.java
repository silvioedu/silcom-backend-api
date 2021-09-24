package com.silcom.manager.api.dto.output;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoIdOutputDTO {
    
    private Long id;
    private Long tipoId;
    private Long corId;
    private Long detalheId;
    private Long complementoId;
    private Long fabricanteId;
    private String codigo;
    private String folder;
    private BigDecimal preco;
    
}
