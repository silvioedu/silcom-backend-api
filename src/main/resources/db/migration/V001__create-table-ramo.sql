CREATE SEQUENCE tblramo_seq;

CREATE TABLE tblramo(
    id bigint not null default nextval ('tblramo_seq'),
    nome varchar(60) not null,
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);

ALTER TABLE tblramo ADD CONSTRAINT tblramo_nome_key UNIQUE(nome);
