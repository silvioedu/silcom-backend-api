package com.silcom.manager.api.dto.output;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProdutoCadastrosOutputDTO {

    List<ProdutoComplementoOutputDTO> complementos;    
    List<ProdutoCorOutputDTO> cores;    
    List<ProdutoDetalheOutputDTO> detalhes;    
    List<ProdutoFabricanteOutputDTO> fabricantes;    
    List<ProdutoTipoOutputDTO> tipos;
}
