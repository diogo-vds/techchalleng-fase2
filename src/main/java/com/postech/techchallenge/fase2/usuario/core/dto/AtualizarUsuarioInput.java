package com.postech.techchallenge.fase2.usuario.core.dto;

public record AtualizarUsuarioInput(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Long tipoUsuarioId
) {
}
