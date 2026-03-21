CREATE TABLE tipos_usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    tipo_usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_usuarios_tipo_usuario
        FOREIGN KEY (tipo_usuario_id)
        REFERENCES tipos_usuario (id)
);

INSERT INTO tipos_usuario (nome)
VALUES ('CLIENTE'),
       ('DONO_RESTAURANTE');
