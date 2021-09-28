CREATE TABLE tblformapagamentotipo(
    id bigint not null,
    nome varchar(100) not null,
    desconto decimal(5,2) not null DEFAULT 0,
    agravo decimal(5,2) not null DEFAULT 0,
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);

ALTER TABLE tblformapagamentotipo ADD CONSTRAINT tblformapagamentotipo_nome_key UNIQUE(nome);
