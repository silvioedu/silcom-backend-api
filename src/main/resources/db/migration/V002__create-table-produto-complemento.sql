CREATE SEQUENCE tblprodutocomplemento_seq;

CREATE TABLE tblprodutocomplemento(
    id bigint not null default nextval ('tblprodutocomplemento_seq'),
    nome varchar(60) not null,
    sigla varchar(01) not null,
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);

ALTER TABLE tblprodutocomplemento ADD CONSTRAINT tblprodutocomplemento_nome_key UNIQUE(nome);
