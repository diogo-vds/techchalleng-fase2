package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.dto.TipoUsuarioOutput;
import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;

import java.util.List;

public class ListarTiposUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    public ListarTiposUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public List<TipoUsuarioOutput> executar() {
        return tipoUsuarioGateway.listarTodos()
                .stream()
                .map(tipoUsuario -> new TipoUsuarioOutput(
                        tipoUsuario.getId(),
                        tipoUsuario.getDescricao()
                ))
                .toList();
    }
}
