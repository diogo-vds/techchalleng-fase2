package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.domain.Usuario;
import com.postech.techchallenge.fase2.usuario.core.dto.AtualizarUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.dto.UsuarioOutput;
import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;
import com.postech.techchallenge.fase2.usuario.core.gateway.UsuarioGateway;

public class AtualizarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final TipoUsuarioGateway tipoUsuarioGateway;

    public AtualizarUsuarioUseCase(UsuarioGateway usuarioGateway,
                                   TipoUsuarioGateway tipoUsuarioGateway) {
        this.usuarioGateway = usuarioGateway;
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public UsuarioOutput executar(AtualizarUsuarioInput input) {

        Usuario usuarioExistente = usuarioGateway
                .buscarPorId(input.id())
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuario nao encontrado"));

        validarDuplicidade(input.id(), input.email(), input.telefone(), input.cpf());

        TipoUsuario tipoUsuario = tipoUsuarioGateway
                .buscarPorId(input.tipoUsuarioId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Tipo de usuario nao encontrado"));

        Usuario usuarioAtualizado = Usuario.reconstruir(
                usuarioExistente.getId(),
                input.nome(),
                input.email(),
                input.telefone(),
                input.cpf(),
                tipoUsuario
        );

        Usuario salvo = usuarioGateway.salvar(usuarioAtualizado);

        return new UsuarioOutput(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.getTelefone(),
                salvo.getCpf(),
                salvo.getTipoUsuario().getDescricao()
        );
    }

    private void validarDuplicidade(Long usuarioId, String email, String telefone, String cpf) {
        usuarioGateway.buscarPorEmail(email)
                .filter(usuario -> !usuario.getId().equals(usuarioId))
                .ifPresent(usuario -> {
                    throw new IllegalArgumentException("Email '" + email + "' ja cadastrado");
                });

        usuarioGateway.buscarPorTelefone(telefone)
                .filter(usuario -> !usuario.getId().equals(usuarioId))
                .ifPresent(usuario -> {
                    throw new IllegalArgumentException("Telefone '" + telefone + "' ja cadastrado");
                });

        usuarioGateway.buscarPorCpf(cpf)
                .filter(usuario -> !usuario.getId().equals(usuarioId))
                .ifPresent(usuario -> {
                    throw new IllegalArgumentException("CPF '" + cpf + "' ja cadastrado");
                });
    }
}
