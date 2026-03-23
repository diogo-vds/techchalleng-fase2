package com.postech.techchallenge.fase2.usuario.infra.persistence;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;
import com.postech.techchallenge.fase2.usuario.infra.persistence.entity.TipoUsuarioEntity;
import com.postech.techchallenge.fase2.usuario.infra.persistence.repository.TipoUsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TipoUsuarioGatewayImpl implements TipoUsuarioGateway {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuarioGatewayImpl(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    public TipoUsuario salvar(TipoUsuario tipoUsuario) {
        TipoUsuarioEntity entity = new TipoUsuarioEntity();
        entity.setId(tipoUsuario.getId());
        entity.setNome(tipoUsuario.getDescricao());

        TipoUsuarioEntity salvo = tipoUsuarioRepository.save(entity);
        return toDomain(salvo);
    }

    @Override
    public Optional<TipoUsuario> buscarPorId(Long id) {
        return tipoUsuarioRepository
                .findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<TipoUsuario> buscarPorDescricao(String descricao) {
        return tipoUsuarioRepository.findByNomeIgnoreCase(descricao)
                .map(this::toDomain);
    }

    @Override
    public List<TipoUsuario> listarTodos() {
        return tipoUsuarioRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deletar(Long id) {
        tipoUsuarioRepository.deleteById(id);
    }

    private TipoUsuario toDomain(TipoUsuarioEntity entity) {
        return new TipoUsuario(
                entity.getId(),
                entity.getNome()
        );
    }
}
