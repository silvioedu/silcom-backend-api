CREATE TABLE tblclienteendereco(
    id bigint not null,
    id_cliente bigint not null REFERENCES tblcliente(id),
    id_endereco_tipo bigint not null REFERENCES tblenderecotipo(id),
    cep varchar(9) not null,
    logradouro varchar(100) not null,
    numero varchar(20) not null,
	complemento varchar(100),
    bairro varchar(60) not null,
	cidade varchar(60) not null,
    estado varchar(2) not null,
    observacoes varchar(255),
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);