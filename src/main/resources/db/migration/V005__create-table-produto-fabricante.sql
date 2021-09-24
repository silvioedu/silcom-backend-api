CREATE SEQUENCE tblprodutofabricante_seq;

CREATE TABLE tblprodutofabricante(
    id bigint not null default nextval ('tblprodutofabricante_seq'),
    nome varchar(20) not null,
    sigla varchar(02) not null,
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);

ALTER TABLE tblprodutofabricante ADD CONSTRAINT tblprodutofabricante_nome_key UNIQUE(nome);
