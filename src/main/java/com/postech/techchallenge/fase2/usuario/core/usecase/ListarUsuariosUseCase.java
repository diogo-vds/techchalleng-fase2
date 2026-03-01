package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.dto.UsuarioOutput;
import com.postech.techchallenge.fase2.usuario.core.gateway.UsuarioGateway;
import com.postech.techchallenge.fase2.usuario.core.domain.Usuario;

import java.util.List;

public class ListarUsuariosUseCase {

    private final UsuarioGateway usuarioGateway;

    public ListarUsuariosUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public List<UsuarioOutput> executar() {

        List<Usuario> usuarios = usuarioGateway.listarTodos();

        return usuarios.stream()
                .map(usuario -> new UsuarioOutput(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getTelefone(),
                        usuario.getCpf(),
                        usuario.getTipoUsuario().getDescricao()
                ))
                .toList();
    }
}
