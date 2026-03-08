package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.domain.Usuario;
import com.postech.techchallenge.fase2.usuario.core.dto.DeletarUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.gateway.UsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeletarUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private DeletarUsuarioUseCase useCase;

    @Test
    void deveDeletarUsuarioComSucesso() {
        // Arrange
        DeletarUsuarioInput input = new DeletarUsuarioInput(10L);
        TipoUsuario tipo = new TipoUsuario(1L, "ADMIN");
        Usuario usuario = Usuario.reconstruir(10L, "João", "joao@email.com", "41999999999", "12345678900", tipo);

        when(usuarioGateway.buscarPorId(10L)).thenReturn(Optional.of(usuario));
        doNothing().when(usuarioGateway).deletar(10L);

        // Act
        assertDoesNotThrow(() -> useCase.executar(input));

        // Assert
        verify(usuarioGateway).deletar(10L);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        // Arrange
        DeletarUsuarioInput input = new DeletarUsuarioInput(10L);

        when(usuarioGateway.buscarPorId(10L)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.executar(input));
        assertEquals("Usuário não encontrado", exception.getMessage());
    }
}
