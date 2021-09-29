CREATE TABLE tblclientevenda(
    id bigint not null,
    id_cliente bigint not null REFERENCES tblcliente(id),
    id_forma_pagamento_tipo bigint not null REFERENCES tblformapagamentotipo(id),
    desconto decimal(5,2) not null DEFAULT 0,
    agravo decimal(5,2) not null DEFAULT 0,
    valor_total decimal(9,2) not null DEFAULT 0,
    emitir_nota boolean,
    observacoes varchar(255),
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);
