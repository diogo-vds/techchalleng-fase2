package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.dto.AtualizarTipoUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.dto.TipoUsuarioOutput;
import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;

public class AtualizarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    public AtualizarTipoUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public TipoUsuarioOutput executar(AtualizarTipoUsuarioInput input) {
        tipoUsuarioGateway.buscarPorId(input.id())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de usuario nao encontrado"));

        TipoUsuario tipoUsuario = tipoUsuarioGateway.salvar(
                new TipoUsuario(input.id(), input.descricao())
        );

        return new TipoUsuarioOutput(
                tipoUsuario.getId(),
                tipoUsuario.getDescricao()
        );
    }
}
