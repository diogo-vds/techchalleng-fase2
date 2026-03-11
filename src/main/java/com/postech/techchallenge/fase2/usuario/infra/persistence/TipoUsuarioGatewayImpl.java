package com.postech.techchallenge.fase2.usuario.infra.persistence;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;
import com.postech.techchallenge.fase2.usuario.infra.persistence.entity.TipoUsuarioEntity;
import com.postech.techchallenge.fase2.usuario.infra.persistence.repository.TipoUsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TipoUsuarioGatewayImpl implements TipoUsuarioGateway {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuarioGatewayImpl(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    public Optional<TipoUsuario> buscarPorId(Long id) {
        return tipoUsuarioRepository
                .findById(id)
                .map(this::toDomain);
    }

    private TipoUsuario toDomain(TipoUsuarioEntity entity) {
        return new TipoUsuario(
                entity.getId(),
                entity.getNome()
        );
    }
}
