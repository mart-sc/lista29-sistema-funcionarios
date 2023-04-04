
    create table funcionario (
       id bigserial not null,
        endereco varchar(255),
        nascimento date,
        nome varchar(255),
        num_dep integer,
        salario numeric(38,2),
        primary key (id)
    );
