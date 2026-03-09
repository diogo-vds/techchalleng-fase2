package com.postech.techchallenge.fase2.endereco.core.dto;

public record EnderecoOutput(
        Long id,
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        String cep
) {
}
