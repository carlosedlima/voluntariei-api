CREATE TABLE Help (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT
);

-- Inserir registros na tabela Help
INSERT INTO Help (titulo, descricao) VALUES ('Testes', 'Testes');
INSERT INTO Help (titulo, descricao) VALUES ('Testes', 'Testes');
INSERT INTO Help (titulo, descricao) VALUES ('Testes', 'Testes');