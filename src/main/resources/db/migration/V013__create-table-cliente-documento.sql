CREATE SEQUENCE tblclientedocumento_seq;

CREATE TABLE tblclientedocumento(
    id bigint not null default nextval ('tblclientedocumento_seq'),
    id_cliente bigint not null REFERENCES tblcliente(id),
    id_tipo_documento bigint not null REFERENCES tbldocumentotipo(id),
    documento varchar(80) not null,
    isento boolean,
    observacoes varchar(255),
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);