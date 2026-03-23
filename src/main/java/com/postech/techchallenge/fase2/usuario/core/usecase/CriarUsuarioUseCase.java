package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.domain.Usuario;
import com.postech.techchallenge.fase2.usuario.core.dto.CriarUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.dto.UsuarioOutput;
import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;
import com.postech.techchallenge.fase2.usuario.core.gateway.UsuarioGateway;

public class CriarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final TipoUsuarioGateway tipoUsuarioGateway;

    public CriarUsuarioUseCase(UsuarioGateway usuarioGateway,
                               TipoUsuarioGateway tipoUsuarioGateway) {
        this.usuarioGateway = usuarioGateway;
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public UsuarioOutput executar(CriarUsuarioInput input) {
        validarDuplicidade(input.email(), input.telefone(), input.cpf());

        TipoUsuario tipoUsuario = tipoUsuarioGateway
                .buscarPorId(input.tipoUsuarioId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Tipo de usuario nao encontrado"));

        Usuario usuario = Usuario.criar(
                input.nome(),
                input.email(),
                input.telefone(),
                input.cpf(),
                tipoUsuario
        );

        Usuario salvo = usuarioGateway.salvar(usuario);

        return new UsuarioOutput(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.getTelefone(),
                salvo.getCpf(),
                salvo.getTipoUsuario().getDescricao()
        );
    }

    private void validarDuplicidade(String email, String telefone, String cpf) {
        usuarioGateway.buscarPorEmail(email)
                .ifPresent(usuario -> {
                    throw new IllegalArgumentException("Email '" + email + "' ja cadastrado");
                });

        usuarioGateway.buscarPorTelefone(telefone)
                .ifPresent(usuario -> {
                    throw new IllegalArgumentException("Telefone '" + telefone + "' ja cadastrado");
                });

        usuarioGateway.buscarPorCpf(cpf)
                .ifPresent(usuario -> {
                    throw new IllegalArgumentException("CPF '" + cpf + "' ja cadastrado");
                });
    }
}
