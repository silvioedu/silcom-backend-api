CREATE TABLE tblclientevendaitem(
    id bigint not null,
    id_cliente_venda bigint not null REFERENCES tblclientevenda(id),
    id_produto bigint not null REFERENCES tblproduto(id),
    tamanho decimal(2,0) not null,
    quantidade smallint not null DEFAULT 0,
    valor_unitario decimal(5,2) not null DEFAULT 0,
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);
