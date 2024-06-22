CREATE TABLE Evento (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    data DATE,
    ong_id BIGINT,
    FOREIGN KEY (ong_id) REFERENCES Ong(id) ON DELETE CASCADE
);

-- Inserir registros na tabela Evento
INSERT INTO Evento (nome, descricao, data, ong_Id) VALUES ('Campanha do Agasalho', 'Distribuição de agasalhos para pessoas carentes', '2024-07-15', 1);
INSERT INTO Evento (nome, descricao, data, ong_Id) VALUES ('Coleta de Alimentos', 'Coleta de alimentos não perecíveis para doação', '2024-08-10', 2);
INSERT INTO Evento (nome, descricao, data, ong_Id) VALUES ('Adoção de Animais', 'Evento para adoção de animais abandonados', '2024-09-20', 3);