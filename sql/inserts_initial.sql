INSERT INTO `zeus`.`perfil` 
(`nome`, `descricao`, `data_atualizacao`, `ativo`)
VALUES
('Administrador', 'Perfil de administradores com acesso a todas as funcionalidades do Zeus.', now(), 1);

INSERT INTO `zeus`.`perfil` 
(`nome`, `descricao`, `data_atualizacao`, `ativo`)
VALUES
('Funcionario', 'Perfil de funcionários.', now(), 1);

INSERT INTO `zeus`.`usuario`
(`login`, `nome`, `senha`, `ativo`, `data_atualizacao`)
VALUES
('admin', 'Administrador', 'totvs@123', 1, now());

INSERT INTO `zeus`.`usuario`
(`login`, `nome`, `senha`, `ativo`, `data_atualizacao`)
VALUES
('func', 'Funcionário', 'totvs@123', 1, now());

INSERT INTO `zeus`.`perfil_usuario`
(`usuario_id`, `perfil_id`, `ativo`, `data_atualizacao`)
VALUES
(1, 1, 1, now());

INSERT INTO `zeus`.`perfil_usuario`
(`usuario_id`, `perfil_id`, `ativo`, `data_atualizacao`)
VALUES
(2, 2, 1, now());