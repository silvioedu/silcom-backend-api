CREATE SEQUENCE tblproduto_seq;

CREATE TABLE tblproduto(
    id bigint not null default nextval ('tblproduto_seq'),
    id_tipo bigint not null REFERENCES tblprodutotipo(id),
    id_cor bigint not null REFERENCES tblprodutocor(id),
    id_detalhe bigint not null REFERENCES tblprodutodetalhe(id),
    id_complemento bigint not null REFERENCES tblprodutocomplemento(id),
    id_fabricante bigint not null REFERENCES tblprodutofabricante(id),
    codigo varchar(08) not null unique,
    folder char(05),
    preco decimal(5,2) not null,
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);

