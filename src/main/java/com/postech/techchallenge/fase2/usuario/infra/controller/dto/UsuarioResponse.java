package com.postech.techchallenge.fase2.usuario.infra.controller.dto;

public record UsuarioResponse(Long id,
                              String nome,
                              String email,
                              String telefone,
                              String cpf,
                              String tipoUsuario
) {}
