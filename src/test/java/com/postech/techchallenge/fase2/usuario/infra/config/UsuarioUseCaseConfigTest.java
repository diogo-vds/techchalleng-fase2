package com.postech.techchallenge.fase2.usuario.infra.config;

import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;
import com.postech.techchallenge.fase2.usuario.core.gateway.UsuarioGateway;
import com.postech.techchallenge.fase2.usuario.core.usecase.AtualizarUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.BuscarUsuarioPorIdUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.CriarUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.DeletarUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.ListarUsuariosUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

class UsuarioUseCaseConfigTest {

    private UsuarioUseCaseConfig config;
    private UsuarioGateway usuarioGateway;
    private TipoUsuarioGateway tipoUsuarioGateway;

    @BeforeEach
    void setup() {
        config = new UsuarioUseCaseConfig();
        usuarioGateway = mock(UsuarioGateway.class);
        tipoUsuarioGateway = mock(TipoUsuarioGateway.class);
    }

    @Test
    void deveCriarBeanCriarUsuarioUseCase() throws Exception {
        CriarUsuarioUseCase useCase = config.criarUsuarioUseCase(usuarioGateway, tipoUsuarioGateway);

        assertNotNull(useCase);
        assertSame(usuarioGateway, obterCampo(useCase, "usuarioGateway"));
        assertSame(tipoUsuarioGateway, obterCampo(useCase, "tipoUsuarioGateway"));
    }

    @Test
    void deveCriarBeanAtualizarUsuarioUseCase() throws Exception {
        AtualizarUsuarioUseCase useCase = config.atualizarUsuarioUseCase(usuarioGateway, tipoUsuarioGateway);

        assertNotNull(useCase);
        assertSame(usuarioGateway, obterCampo(useCase, "usuarioGateway"));
        assertSame(tipoUsuarioGateway, obterCampo(useCase, "tipoUsuarioGateway"));
    }

    @Test
    void deveCriarBeanBuscarUsuarioPorIdUseCase() throws Exception {
        BuscarUsuarioPorIdUseCase useCase = config.buscarUsuarioPorIdUseCase(usuarioGateway);

        assertNotNull(useCase);
        assertSame(usuarioGateway, obterCampo(useCase, "usuarioGateway"));
    }

    @Test
    void deveCriarBeanListarUsuariosUseCase() throws Exception {
        ListarUsuariosUseCase useCase = config.listarUsuariosUseCase(usuarioGateway);

        assertNotNull(useCase);
        assertSame(usuarioGateway, obterCampo(useCase, "usuarioGateway"));
    }

    @Test
    void deveCriarBeanDeletarUsuarioUseCase() throws Exception {
        DeletarUsuarioUseCase useCase = config.deletarUsuarioUseCase(usuarioGateway);

        assertNotNull(useCase);
        assertSame(usuarioGateway, obterCampo(useCase, "usuarioGateway"));
    }

    private Object obterCampo(Object target, String nomeCampo) throws Exception {
        Field field = target.getClass().getDeclaredField(nomeCampo);
        field.setAccessible(true);
        return field.get(target);
    }
}
