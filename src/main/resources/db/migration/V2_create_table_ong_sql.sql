CREATE TABLE Ong (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);
-- Inserir registros na tabela Ong
INSERT INTO Ong (nome, email, senha) VALUES ('Ong do Bem', 'contato@ongdobem.org', 'senhaong1');
INSERT INTO Ong (nome, email, senha) VALUES ('Ajuda ao Próximo', 'info@ajudaaoproximo.org', 'senhaong2');
INSERT INTO Ong (nome, email, senha) VALUES ('Mãos Unidas', 'contact@maosunidas.org', 'senhaong3');
