CREATE TABLE enderecos (
    id BIGSERIAL PRIMARY KEY,
    rua VARCHAR(255) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    complemento VARCHAR(255),
    bairro VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    uf CHAR(2) NOT NULL,
    cep CHAR(8) NOT NULL,
    CONSTRAINT chk_enderecos_uf_length CHECK (char_length(uf) = 2),
    CONSTRAINT chk_enderecos_cep_length CHECK (char_length(cep) = 8)
);
