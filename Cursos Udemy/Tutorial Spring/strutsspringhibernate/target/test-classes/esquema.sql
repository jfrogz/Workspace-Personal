DROP TABLE IF EXISTS PERSONA;
CREATE TABLE PERSONA(
id_persona IDENTITY PRIMARY KEY ,
nombre varchar(50) not null,
ape_paterno VARCHAR(50) not null,
ape_materno VARCHAR(50),
email VARCHAR (50) not NULL UNIQUE
);