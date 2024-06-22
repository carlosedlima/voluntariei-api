CREATE TABLE APP_USER (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL UNIQUE
);

-- Inserir registros na tabela User
INSERT INTO app_user (nome, email, senha, cpf) VALUES ('João Silva', 'joao.silva@example.com', 'senha123', '12345678901');
INSERT INTO app_user (nome, email, senha, cpf) VALUES ('Maria Oliveira', 'maria.oliveira@example.com', 'senha456', '10987654321');
INSERT INTO app_user (nome, email, senha, cpf) VALUES ('Pedro Souza', 'pedro.souza@example.com', 'senha789', '11223344556');