drop table if exists persona;
create table persona (
id_persona IDENTITY PRIMARY KEY,
nombre VARCHAR(50) not null,
ape_paterno varchar(50) not null,
ape_materno VARCHAR(50),
email VARCHAR(50) not null UNIQUE
);