CREATE TABLE tblcliente(
    id bigint not null,
    razao_social varchar(100) not null,
    nome_fantasia varchar(100),
    id_ramo bigint not null REFERENCES tblramo(id),
    tipo_pessoa varchar(01) check(tipo_pessoa in('F','J')),
    observacoes varchar(255),
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);

ALTER TABLE tblcliente ADD CONSTRAINT tblcliente_razao_social_key UNIQUE(razao_social);