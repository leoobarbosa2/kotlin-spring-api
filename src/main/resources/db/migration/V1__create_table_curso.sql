CREATE TABLE curso (
    id bigint not null GENERATED ALWAYS AS IDENTITY,
    nome varchar(50) not null,
    categoria varchar(50) not null,
    primary key (id)
);

INSERT INTO curso VALUES (1, 'Kotlin', 'Programação')