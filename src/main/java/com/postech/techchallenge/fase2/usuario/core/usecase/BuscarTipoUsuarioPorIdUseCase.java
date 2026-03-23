package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.dto.TipoUsuarioOutput;
import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;

public class BuscarTipoUsuarioPorIdUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    public BuscarTipoUsuarioPorIdUseCase(TipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public TipoUsuarioOutput executar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id do tipo de usuario e obrigatorio");
        }

        var tipoUsuario = tipoUsuarioGateway.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de usuario nao encontrado"));

        return new TipoUsuarioOutput(
                tipoUsuario.getId(),
                tipoUsuario.getDescricao()
        );
    }
}
