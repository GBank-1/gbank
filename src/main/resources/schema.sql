-- Clientes
 create table cliente (
        id integer generated by default as identity,
        nome varchar(255),
        sobrenome varchar(255),
        password varchar(255),
        primary key (id)
);
-- Agencia
 create table Agencia (
         codigo char(4)
 );
