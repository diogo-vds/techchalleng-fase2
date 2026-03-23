CREATE TABLE cardapio (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    caminho_foto VARCHAR(255),
    preco NUMERIC(7,2) NOT NULL,
    disponivel_apenas_restaurante BOOLEAN NOT NULL
);