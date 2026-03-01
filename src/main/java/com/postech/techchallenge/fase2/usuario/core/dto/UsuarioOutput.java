package com.postech.techchallenge.fase2.usuario.core.dto;

public record UsuarioOutput(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        String tipoUsuario
) {
}
