package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.dto.CriarTipoUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.dto.TipoUsuarioOutput;
import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;

public class CriarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    public CriarTipoUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public TipoUsuarioOutput executar(CriarTipoUsuarioInput input) {
        tipoUsuarioGateway.buscarPorDescricao(input.descricao())
                .ifPresent(tipoUsuario -> {
                    throw new IllegalArgumentException(
                            "Tipo de usuario '" + input.descricao() + "' ja cadastrado"
                    );
                });

        TipoUsuario tipoUsuario = tipoUsuarioGateway.salvar(
                new TipoUsuario(null, input.descricao())
        );

        return new TipoUsuarioOutput(
                tipoUsuario.getId(),
                tipoUsuario.getDescricao()
        );
    }
}
