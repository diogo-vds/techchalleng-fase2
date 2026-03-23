package com.postech.techchallenge.fase2.usuario.infra.config;

import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;
import com.postech.techchallenge.fase2.usuario.core.gateway.UsuarioGateway;
import com.postech.techchallenge.fase2.usuario.core.usecase.AtualizarUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.AtualizarTipoUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.BuscarTipoUsuarioPorIdUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.BuscarUsuarioPorIdUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.CriarTipoUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.CriarUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.DeletarTipoUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.DeletarUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.ListarTiposUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.ListarUsuariosUseCase;
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

    @Bean
    public BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase(
            TipoUsuarioGateway tipoUsuarioGateway
    ) {
        return new BuscarTipoUsuarioPorIdUseCase(tipoUsuarioGateway);
    }

    @Bean
    public ListarTiposUsuarioUseCase listarTiposUsuarioUseCase(
            TipoUsuarioGateway tipoUsuarioGateway
    ) {
        return new ListarTiposUsuarioUseCase(tipoUsuarioGateway);
    }

    @Bean
    public CriarTipoUsuarioUseCase criarTipoUsuarioUseCase(
            TipoUsuarioGateway tipoUsuarioGateway
    ) {
        return new CriarTipoUsuarioUseCase(tipoUsuarioGateway);
    }

    @Bean
    public AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase(
            TipoUsuarioGateway tipoUsuarioGateway
    ) {
        return new AtualizarTipoUsuarioUseCase(tipoUsuarioGateway);
    }

    @Bean
    public DeletarTipoUsuarioUseCase deletarTipoUsuarioUseCase(
            TipoUsuarioGateway tipoUsuarioGateway
    ) {
        return new DeletarTipoUsuarioUseCase(tipoUsuarioGateway);
    }
}
