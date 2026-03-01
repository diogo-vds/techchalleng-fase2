package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.dto.DeletarUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.gateway.UsuarioGateway;

public class DeletarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public DeletarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public void executar(DeletarUsuarioInput input) {

        usuarioGateway.buscarPorId(input.id())
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuário não encontrado"));

        usuarioGateway.deletar(input.id());
    }
}
