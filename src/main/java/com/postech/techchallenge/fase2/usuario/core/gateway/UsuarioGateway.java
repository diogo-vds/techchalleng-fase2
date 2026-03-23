package com.postech.techchallenge.fase2.usuario.core.gateway;

import com.postech.techchallenge.fase2.usuario.core.domain.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioGateway {

    Usuario salvar(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> buscarPorTelefone(String telefone);

    Optional<Usuario> buscarPorCpf(String cpf);

    List<Usuario> listarTodos();

    void deletar(Long id);

}
