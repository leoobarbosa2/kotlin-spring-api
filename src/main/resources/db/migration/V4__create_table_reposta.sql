CREATE TABLE IF NOT EXISTS usuario (
    id bigint not null GENERATED ALWAYS AS IDENTITY,
    mensagem varchar(300) not null,
    data_criacao timestamp not null,
    topico_id bigint not null,
    autor_id bigint not null,
    solucao boolean not null,
    primary key (id),
    foreign key (autor_id) references usuario(id),
    foreign key (topico_id) references topico(id)
);