-- MySQL Workbench Synchronization
-- Generated: 2017-04-20 16:09
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Praxis

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `recursos_humanos` DEFAULT CHARACTER SET utf8 ;
USE recursos_humanos;
CREATE TABLE IF NOT EXISTS `recursos_humanos`.`persona` (
  `id_persona` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido_paterno` VARCHAR(45) NOT NULL,
  `apellido_materno` VARCHAR(45) NULL DEFAULT NULL,
  `telefono` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_persona`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `recursos_humanos`.`Usuario` (
  `id_Usuario` INT(11) NOT NULL AUTO_INCREMENT,
  `id_persona` INT(11) NULL DEFAULT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_Usuario`),
  INDEX `fk_Usuario_persona_idx` (`id_persona` ASC),
  CONSTRAINT `fk_Usuario_persona`
  FOREIGN KEY (`id_persona`)
  REFERENCES `recursos_humanos`.`persona` (`id_persona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


insert INTO persona (nombre, apellido_paterno, apellido_materno, email, telefono) VALUES ('Juan', 'Perez', null, 'juanperez@gmail.com', null);
insert INTO persona (nombre, apellido_paterno, apellido_materno, email, telefono) VALUES ('Oscar', 'Gomez', 'Larios', 'ogomez@gmail.com', '555245878');
insert INTO persona (nombre, apellido_paterno, apellido_materno, email, telefono) VALUES ('Angelica', 'Lara', 'Gomez', 'alara@gmail.com', '222545854');