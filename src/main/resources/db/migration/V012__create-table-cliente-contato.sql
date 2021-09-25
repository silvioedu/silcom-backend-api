CREATE SEQUENCE tblclientecontato_seq;

CREATE TABLE tblclientecontato(
    id bigint not null default nextval ('tblclientecontato_seq'),
    id_cliente bigint not null REFERENCES tblcliente(id),
    id_tipo_contato bigint not null REFERENCES tblcontatotipo(id),
    contato varchar(80) not null,
    observacoes varchar(255),
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);