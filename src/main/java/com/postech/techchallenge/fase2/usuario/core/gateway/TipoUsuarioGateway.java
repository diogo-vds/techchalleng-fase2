package com.postech.techchallenge.fase2.usuario.core.gateway;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;

import java.util.Optional;

public interface TipoUsuarioGateway {

    Optional<TipoUsuario> buscarPorId(Long id);
}
