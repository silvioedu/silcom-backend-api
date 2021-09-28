package com.silcom.manager.domain.enums;

import lombok.Getter;

@Getter
public enum MaskDocumentoEnum {
    
    CPF(1, "###.###.###-##", 11),
    CNPJ(2, "###.###.###/####-##", 14),
    IE(3,"###.###.###.###", 12);

    private int id;
    private String format;
    private int digits;

    MaskDocumentoEnum(int id, String format, int digits) {
        this.id = id;
        this.format = format;
        this.digits = digits;
    }

}
