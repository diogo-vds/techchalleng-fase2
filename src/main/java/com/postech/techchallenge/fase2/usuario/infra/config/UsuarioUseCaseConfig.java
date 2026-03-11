package com.postech.techchallenge.fase2.usuario.infra.config;

import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;
import com.postech.techchallenge.fase2.usuario.core.gateway.UsuarioGateway;
import com.postech.techchallenge.fase2.usuario.core.usecase.AtualizarUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.BuscarUsuarioPorIdUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.CriarUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.ListarUsuariosUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.DeletarUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioUseCaseConfig {

    @Bean
    public CriarUsuarioUseCase criarUsuarioUseCase(
            UsuarioGateway usuarioGateway,
            TipoUsuarioGateway tipoUsuarioGateway
    ) {
        return new CriarUsuarioUseCase(usuarioGateway, tipoUsuarioGateway);
    }

    @Bean
    public AtualizarUsuarioUseCase atualizarUsuarioUseCase(
            UsuarioGateway usuarioGateway,
            TipoUsuarioGateway tipoUsuarioGateway
    ) {
        return new AtualizarUsuarioUseCase(usuarioGateway, tipoUsuarioGateway);
    }

    @Bean
    public BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase(
            UsuarioGateway usuarioGateway
    ) {
        return new BuscarUsuarioPorIdUseCase(usuarioGateway);
    }

    @Bean
    public ListarUsuariosUseCase listarUsuariosUseCase(
            UsuarioGateway usuarioGateway
    ) {
        return new ListarUsuariosUseCase(usuarioGateway);
    }

    @Bean
    public DeletarUsuarioUseCase deletarUsuarioUseCase(
            UsuarioGateway usuarioGateway
    ) {
        return new DeletarUsuarioUseCase(usuarioGateway);
    }
}