package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.dto.BuscarUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.dto.UsuarioOutput;
import com.postech.techchallenge.fase2.usuario.core.gateway.UsuarioGateway;
import com.postech.techchallenge.fase2.usuario.core.domain.Usuario;

public class BuscarUsuarioPorIdUseCase {

    private final UsuarioGateway usuarioGateway;

    public BuscarUsuarioPorIdUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public UsuarioOutput executar(BuscarUsuarioInput input) {

        Usuario usuario = usuarioGateway
                .buscarPorId(input.id())
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuário não encontrado"));

        return new UsuarioOutput(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getCpf(),
                usuario.getTipoUsuario().getDescricao()
        );
    }
}
