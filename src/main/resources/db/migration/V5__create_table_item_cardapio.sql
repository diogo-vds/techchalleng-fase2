CREATE TABLE item_cardapio (
   id BIGSERIAL PRIMARY KEY,
   cardapio_id BIGSERIAL NOT NULL,
   nome VARCHAR(255) NOT NULL,
   descricao VARCHAR(255) NOT NULL,
   caminho_foto VARCHAR(255),
   preco NUMERIC(7,2) NOT NULL,
   disponivel_apenas_restaurante BOOLEAN NOT NULL,

    CONSTRAINT fk_cardapio
        FOREIGN KEY (cardapio_id)
        REFERENCES cardapio(id)
);