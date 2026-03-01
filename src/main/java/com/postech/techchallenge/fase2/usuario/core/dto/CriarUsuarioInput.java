package com.postech.techchallenge.fase2.usuario.core.dto;

public record CriarUsuarioInput(
        String nome,
        String email,
        String telefone,
        String cpf,
        Long tipoUsuarioId
) {
}
