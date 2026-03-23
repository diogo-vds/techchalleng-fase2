package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.dto.DeletarTipoUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeletarTipoUsuarioUseCaseTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @InjectMocks
    private DeletarTipoUsuarioUseCase useCase;

    @Test
    void deveDeletarTipoUsuario() {
        when(tipoUsuarioGateway.buscarPorId(1L))
                .thenReturn(Optional.of(new TipoUsuario(1L, "CLIENTE")));
        doNothing().when(tipoUsuarioGateway).deletar(1L);

        assertDoesNotThrow(() -> useCase.executar(new DeletarTipoUsuarioInput(1L)));

        verify(tipoUsuarioGateway).deletar(1L);
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoEncontrado() {
        when(tipoUsuarioGateway.buscarPorId(1L))
                .thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> useCase.executar(new DeletarTipoUsuarioInput(1L)));

        assertEquals("Tipo de usuario nao encontrado", exception.getMessage());
    }
}
