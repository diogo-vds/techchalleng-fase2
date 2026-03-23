package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.dto.AtualizarTipoUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.dto.TipoUsuarioOutput;
import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizarTipoUsuarioUseCaseTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @InjectMocks
    private AtualizarTipoUsuarioUseCase useCase;

    @Test
    void deveAtualizarTipoUsuario() {
        when(tipoUsuarioGateway.buscarPorId(1L))
                .thenReturn(Optional.of(new TipoUsuario(1L, "CLIENTE")));
        when(tipoUsuarioGateway.salvar(any(TipoUsuario.class)))
                .thenReturn(new TipoUsuario(1L, "ADMIN"));

        TipoUsuarioOutput output = useCase.executar(new AtualizarTipoUsuarioInput(1L, "ADMIN"));

        assertEquals(1L, output.id());
        assertEquals("ADMIN", output.descricao());
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoEncontrado() {
        when(tipoUsuarioGateway.buscarPorId(1L))
                .thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> useCase.executar(new AtualizarTipoUsuarioInput(1L, "ADMIN")));

        assertEquals("Tipo de usuario nao encontrado", exception.getMessage());
    }
}
