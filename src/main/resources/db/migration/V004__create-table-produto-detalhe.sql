CREATE SEQUENCE tblprodutodetalhe_seq;

CREATE TABLE tblprodutodetalhe(
    id bigint not null default nextval ('tblprodutodetalhe_seq'),
    nome varchar(20) not null,
    sigla varchar(01) not null,
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);

ALTER TABLE tblprodutodetalhe ADD CONSTRAINT tblprodutodetalhe_nome_key UNIQUE(nome);
