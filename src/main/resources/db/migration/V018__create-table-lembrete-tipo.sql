CREATE TABLE tbllembretetipo(
    id bigint not null,
    nome varchar(20) not null,
    intervalo decimal(2,0) not null,
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);

ALTER TABLE tbllembretetipo ADD CONSTRAINT tbllembretetipo_nome_key UNIQUE(nome);
