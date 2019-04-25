CREATE TABLE IF NOT EXISTS `zeus`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(150) NOT NULL,
  `senha` VARCHAR(40) NOT NULL,
  `ativo` TINYINT NOT NULL DEFAULT 1,
  `data_atualizacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `zeus`.`perfil` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `data_atualizacao` DATETIME NOT NULL,
  `ativo` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `zeus`.`perfil_usuario` (
  `usuario_id` INT NOT NULL,
  `perfil_id` INT NOT NULL,
  `ativo` TINYINT NOT NULL DEFAULT 1,
  `data_atualizacao` DATETIME NOT NULL,
  PRIMARY KEY (`usuario_id`, `perfil_id`),
  INDEX `fk_perfil_usuario_perfil_idx` (`perfil_id` ASC),
  INDEX `fk_perfil_usuario_usuario_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_perfil_usuario_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `zeus`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_perfil_usuario__perfil`
    FOREIGN KEY (`perfil_id`)
    REFERENCES `zeus`.`perfil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;