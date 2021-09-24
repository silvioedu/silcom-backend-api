CREATE SEQUENCE tblprodutotipo_seq;

CREATE TABLE tblprodutotipo(
    id bigint not null default nextval ('tblprodutotipo_seq'),
    nome varchar(20) not null,
    sigla varchar(01) not null,
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);

ALTER TABLE tblprodutotipo ADD CONSTRAINT tblprodutotipo_nome_key UNIQUE(nome);
