CREATE SEQUENCE tblcontatotipo_seq;

CREATE TABLE tblcontatotipo(
    id bigint not null default nextval ('tblcontatotipo_seq'),
    nome varchar(20) not null,
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);

ALTER TABLE tblcontatotipo ADD CONSTRAINT tblcontatotipo_nome_key UNIQUE(nome);
