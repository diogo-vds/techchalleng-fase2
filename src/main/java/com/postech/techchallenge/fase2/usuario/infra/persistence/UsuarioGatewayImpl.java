package com.postech.techchallenge.fase2.usuario.infra.persistence;
import com.postech.techchallenge.fase2.usuario.core.domain.Usuario;
import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.gateway.UsuarioGateway;
import com.postech.techchallenge.fase2.usuario.infra.persistence.entity.UsuarioEntity;
import com.postech.techchallenge.fase2.usuario.infra.persistence.entity.TipoUsuarioEntity;
import com.postech.techchallenge.fase2.usuario.infra.persistence.repository.UsuarioRepository;
import com.postech.techchallenge.fase2.usuario.infra.persistence.repository.TipoUsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;

    public UsuarioGatewayImpl(
            UsuarioRepository usuarioRepository,
            TipoUsuarioRepository tipoUsuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {

        UsuarioEntity entity = toEntity(usuario);
        UsuarioEntity salvo = usuarioRepository.save(entity);

        return toDomain(salvo);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmailIgnoreCase(email)
                .map(this::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorTelefone(String telefone) {
        return usuarioRepository.findByTelefone(telefone)
                .map(this::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf)
                .map(this::toDomain);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    // =========================
    // MAPPERS
    // =========================

    private UsuarioEntity toEntity(Usuario usuario) {

        TipoUsuarioEntity tipoEntity = tipoUsuarioRepository
                .findById(usuario.getTipoUsuario().getId())
                .orElseThrow(() ->
                        new RuntimeException("TipoUsuario não encontrado"));

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());
        entity.setNome(usuario.getNome());
        entity.setEmail(usuario.getEmail());
        entity.setTelefone(usuario.getTelefone());
        entity.setCpf(usuario.getCpf());
        entity.setTipoUsuario(tipoEntity);

        return entity;
    }

    private Usuario toDomain(UsuarioEntity entity) {

        TipoUsuario tipoUsuario = new TipoUsuario(
                entity.getTipoUsuario().getId(),
                entity.getTipoUsuario().getNome()
        );

        return Usuario.reconstruir(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getCpf(),
                tipoUsuario
        );
    }
}
