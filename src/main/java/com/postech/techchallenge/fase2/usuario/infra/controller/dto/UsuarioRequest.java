package com.postech.techchallenge.fase2.usuario.infra.controller.dto;

public record UsuarioRequest (
        String nome,
        String email,
        String telefone,
        String cpf,
        Long tipoUsuarioId
) {}
