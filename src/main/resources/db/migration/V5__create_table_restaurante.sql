CREATE TABLE restaurante (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    endereco_id BIGINT NOT NULL,
    tipo_cozinha VARCHAR(255),
    horario_funcionamento VARCHAR(255) NOT NULL,
    dono_id BIGINT NOT NULL,
    cardapio_id BIGINT,

    CONSTRAINT fk_restaurante_endereco
        FOREIGN KEY (endereco_id)
        REFERENCES enderecos(id),

    CONSTRAINT fk_restaurante_cardapio
        FOREIGN KEY (cardapio_id)
        REFERENCES cardapio(id)
);