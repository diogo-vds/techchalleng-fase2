CREATE TABLE restaurante (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    tipo_cozinha VARCHAR(255),
    horario_Funcionamento VARCHAR(255) NOT NULL,
    dono_id BIGSERIAL NOT NULL
);