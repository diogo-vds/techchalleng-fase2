package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.dto.CriarTipoUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.dto.TipoUsuarioOutput;
import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarTipoUsuarioUseCaseTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @InjectMocks
    private CriarTipoUsuarioUseCase useCase;

    @Test
    void deveCriarTipoUsuario() {
        when(tipoUsuarioGateway.buscarPorDescricao("CLIENTE"))
                .thenReturn(java.util.Optional.empty());
        when(tipoUsuarioGateway.salvar(any(TipoUsuario.class)))
                .thenReturn(new TipoUsuario(1L, "CLIENTE"));

        TipoUsuarioOutput output = useCase.executar(new CriarTipoUsuarioInput("CLIENTE"));

        assertEquals(1L, output.id());
        assertEquals("CLIENTE", output.descricao());
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioJaExistir() {
        when(tipoUsuarioGateway.buscarPorDescricao("CLIENTE"))
                .thenReturn(java.util.Optional.of(new TipoUsuario(1L, "CLIENTE")));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> useCase.executar(new CriarTipoUsuarioInput("CLIENTE")));

        assertEquals("Tipo de usuario 'CLIENTE' ja cadastrado", exception.getMessage());
    }
}
