package com.postech.techchallenge.fase2.usuario.core.gateway;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;

import java.util.List;
import java.util.Optional;

public interface TipoUsuarioGateway {

    TipoUsuario salvar(TipoUsuario tipoUsuario);

    Optional<TipoUsuario> buscarPorId(Long id);

    Optional<TipoUsuario> buscarPorDescricao(String descricao);

    List<TipoUsuario> listarTodos();

    void deletar(Long id);
}
