CREATE TABLE tblclientelembrete(
    id bigint not null,
    id_cliente bigint not null REFERENCES tblcliente(id),
    id_tipo_lembrete bigint not null REFERENCES tbllembretetipo(id),
    data_evento date not null,
    observacoes varchar(255),
    data_criacao timestamp(0) not null,
    data_atualizacao timestamp(0) not null,
    PRIMARY KEY (id)
);