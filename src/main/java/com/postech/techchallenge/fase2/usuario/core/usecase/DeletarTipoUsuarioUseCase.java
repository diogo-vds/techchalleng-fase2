package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.dto.DeletarTipoUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;

public class DeletarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    public DeletarTipoUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public void executar(DeletarTipoUsuarioInput input) {
        tipoUsuarioGateway.buscarPorId(input.id())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de usuario nao encontrado"));

        tipoUsuarioGateway.deletar(input.id());
    }
}
