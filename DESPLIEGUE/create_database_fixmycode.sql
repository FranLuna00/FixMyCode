-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema fixmycode
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fixmycode
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fixmycode` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;
USE `fixmycode` ;

-- -----------------------------------------------------
-- Table `fixmycode`.`archivos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fixmycode`.`archivos` ;

CREATE TABLE IF NOT EXISTS `fixmycode`.`archivos` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `archivo` VARCHAR(255) NULL DEFAULT NULL,
  `tipoArchivo` ENUM('MARCA', 'JAVASCRIPT', 'CSS', 'SASS', 'JAVA', 'SQL', 'PROPERTIES') NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `fixmycode`.`etiquetas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fixmycode`.`etiquetas` ;

CREATE TABLE IF NOT EXISTS `fixmycode`.`etiquetas` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `fixmycode`.`perfiles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fixmycode`.`perfiles` ;

CREATE TABLE IF NOT EXISTS `fixmycode`.`perfiles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `rol` ENUM('REGISTRADO', 'ADMIN') NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `fixmycode`.`usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fixmycode`.`usuarios` ;

CREATE TABLE IF NOT EXISTS `fixmycode`.`usuarios` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `avatar` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NOT NULL,
  `enabled` INT(11) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `passwd` VARCHAR(255) NOT NULL,
  `username` VARCHAR(30) NOT NULL,
  `idPerfil` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_usuarios_Email` (`email` ASC) VISIBLE,
  UNIQUE INDEX `UK_usuarios_Username` (`username` ASC) VISIBLE,
  INDEX `FKds7f4b6hm5kmtfdi95i3xl8it` (`idPerfil` ASC) VISIBLE,
  CONSTRAINT `FKds7f4b6hm5kmtfdi95i3xl8it`
    FOREIGN KEY (`idPerfil`)
    REFERENCES `fixmycode`.`perfiles` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `fixmycode`.`publicaciones`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fixmycode`.`publicaciones` ;

CREATE TABLE IF NOT EXISTS `fixmycode`.`publicaciones` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `detalles` VARCHAR(600) NOT NULL,
  `fechaPublicacion` DATETIME(6) NULL DEFAULT NULL,
  `titulo` VARCHAR(60) NOT NULL,
  `usuario_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKcuualw35fb3065r7mjiijb898` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `FKcuualw35fb3065r7mjiijb898`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `fixmycode`.`usuarios` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `fixmycode`.`publicaciones_archivos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fixmycode`.`publicaciones_archivos` ;

CREATE TABLE IF NOT EXISTS `fixmycode`.`publicaciones_archivos` (
  `Publicacion_id` INT(11) NOT NULL,
  `archivos_id` INT(11) NOT NULL,
  UNIQUE INDEX `UK_8mg2vhnxbedywl1b5bkeur5k` (`archivos_id` ASC) VISIBLE,
  INDEX `FKor7fpi3u4j7dnbm3nth93vifp` (`Publicacion_id` ASC) VISIBLE,
  CONSTRAINT `FKexu7e72wht8d4iqj0ji9g3ahb`
    FOREIGN KEY (`archivos_id`)
    REFERENCES `fixmycode`.`archivos` (`id`),
  CONSTRAINT `FKor7fpi3u4j7dnbm3nth93vifp`
    FOREIGN KEY (`Publicacion_id`)
    REFERENCES `fixmycode`.`publicaciones` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `fixmycode`.`publicaciones_etiquetas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fixmycode`.`publicaciones_etiquetas` ;

CREATE TABLE IF NOT EXISTS `fixmycode`.`publicaciones_etiquetas` (
  `Publicacion_id` INT(11) NOT NULL,
  `etiquetas_id` INT(11) NOT NULL,
  INDEX `FK5tq28ykoyso1l0mi8wxcabn4t` (`etiquetas_id` ASC) VISIBLE,
  INDEX `FKpr92tn5ycgn5s8omvyi9v88bl` (`Publicacion_id` ASC) VISIBLE,
  CONSTRAINT `FK5tq28ykoyso1l0mi8wxcabn4t`
    FOREIGN KEY (`etiquetas_id`)
    REFERENCES `fixmycode`.`etiquetas` (`id`),
  CONSTRAINT `FKpr92tn5ycgn5s8omvyi9v88bl`
    FOREIGN KEY (`Publicacion_id`)
    REFERENCES `fixmycode`.`publicaciones` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `fixmycode`.`respuestas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fixmycode`.`respuestas` ;

CREATE TABLE IF NOT EXISTS `fixmycode`.`respuestas` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `respuesta` VARCHAR(400) NULL DEFAULT NULL,
  `publicacion_id` INT(11) NULL DEFAULT NULL,
  `usuario_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK7yo3yvomj8cco5n47o6x57fno` (`publicacion_id` ASC) VISIBLE,
  INDEX `FK64htn6ybgsflpvkjwoqt84phd` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `FK64htn6ybgsflpvkjwoqt84phd`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `fixmycode`.`usuarios` (`id`),
  CONSTRAINT `FK7yo3yvomj8cco5n47o6x57fno`
    FOREIGN KEY (`publicacion_id`)
    REFERENCES `fixmycode`.`publicaciones` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `fixmycode`.`publicaciones_respuestas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fixmycode`.`publicaciones_respuestas` ;

CREATE TABLE IF NOT EXISTS `fixmycode`.`publicaciones_respuestas` (
  `Publicacion_id` INT(11) NOT NULL,
  `respuestas_id` INT(11) NOT NULL,
  UNIQUE INDEX `UK_jbxwdogp0vkb5gr6pdl1rm22u` (`respuestas_id` ASC) VISIBLE,
  INDEX `FKeq3sx62nreycvuqeelurwxx2x` (`Publicacion_id` ASC) VISIBLE,
  CONSTRAINT `FKeq3sx62nreycvuqeelurwxx2x`
    FOREIGN KEY (`Publicacion_id`)
    REFERENCES `fixmycode`.`publicaciones` (`id`),
  CONSTRAINT `FKqcgf1rsetbegylgq4ofy90vks`
    FOREIGN KEY (`respuestas_id`)
    REFERENCES `fixmycode`.`respuestas` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `fixmycode`.`valoraciones`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fixmycode`.`valoraciones` ;

CREATE TABLE IF NOT EXISTS `fixmycode`.`valoraciones` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `valoracion` VARCHAR(255) NULL DEFAULT NULL,
  `usuario_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKmtbedrv2q0wjdsrvnb57g8whw` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `FKmtbedrv2q0wjdsrvnb57g8whw`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `fixmycode`.`usuarios` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `fixmycode`.`publicaciones_valoraciones`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fixmycode`.`publicaciones_valoraciones` ;

CREATE TABLE IF NOT EXISTS `fixmycode`.`publicaciones_valoraciones` (
  `Publicacion_id` INT(11) NOT NULL,
  `valoraciones_id` INT(11) NOT NULL,
  UNIQUE INDEX `UK_nntvi51mlotxx7u56e9lc8m5i` (`valoraciones_id` ASC) VISIBLE,
  INDEX `FKr0ot0gkc3d1uk2i89h4yh6ld3` (`Publicacion_id` ASC) VISIBLE,
  CONSTRAINT `FKap6uf415dk56hy4yite4xqexq`
    FOREIGN KEY (`valoraciones_id`)
    REFERENCES `fixmycode`.`valoraciones` (`id`),
  CONSTRAINT `FKr0ot0gkc3d1uk2i89h4yh6ld3`
    FOREIGN KEY (`Publicacion_id`)
    REFERENCES `fixmycode`.`publicaciones` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `fixmycode`.`usuarios_publicaciones`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fixmycode`.`usuarios_publicaciones` ;

CREATE TABLE IF NOT EXISTS `fixmycode`.`usuarios_publicaciones` (
  `Usuario_id` INT(11) NOT NULL,
  `publicaciones_id` INT(11) NOT NULL,
  UNIQUE INDEX `UK_4b45aj3h5ftkx86lom7iv9hji` (`publicaciones_id` ASC) VISIBLE,
  INDEX `FKkfuvvai9gk1li97wr7alup4hf` (`Usuario_id` ASC) VISIBLE,
  CONSTRAINT `FK6nu9rh1uif8a2xl9itm8wkgfk`
    FOREIGN KEY (`publicaciones_id`)
    REFERENCES `fixmycode`.`publicaciones` (`id`),
  CONSTRAINT `FKkfuvvai9gk1li97wr7alup4hf`
    FOREIGN KEY (`Usuario_id`)
    REFERENCES `fixmycode`.`usuarios` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
