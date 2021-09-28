package com.silcom.manager.domain.enums;

import lombok.Getter;

@Getter
public enum MaskEnum {
    
    CPF(1, "###.###.###-##", 11),
    CNPJ(2, "###.###.###/####-##", 14),
    IE(3,"###.###.###.###", 12),
    CEP(4, "#####-###", 8),
    TELEFONE(5, "(##) ####-####", 10),
    TELEFONE_CELULAR(6, "(##) #####-####", 11);

    private int id;
    private String format;
    private int digits;

    MaskEnum(int id, String format, int digits) {
        this.id = id;
        this.format = format;
        this.digits = digits;
    }

}
