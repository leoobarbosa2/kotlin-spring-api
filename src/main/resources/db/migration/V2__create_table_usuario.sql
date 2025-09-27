CREATE TABLE IF NOT EXISTS usuario (
    id bigint not null GENERATED ALWAYS AS IDENTITY,
    nome varchar(50) not null,
    email varchar(50) not null,
    primary key (id)
);

INSERT INTO usuario VALUES (1, 'Leonardo', 'leonardo@mail.com')