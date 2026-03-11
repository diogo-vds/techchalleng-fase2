package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.domain.Usuario;
import com.postech.techchallenge.fase2.usuario.core.dto.AtualizarUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.dto.UsuarioOutput;
import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;
import com.postech.techchallenge.fase2.usuario.core.gateway.UsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AtualizarUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @InjectMocks
    private AtualizarUsuarioUseCase useCase;

    @Test
    void deveAtualizarUsuarioComSucesso() {
        // Arrange
        AtualizarUsuarioInput input = new AtualizarUsuarioInput(10L, "João Atualizado", "joao@email.com", "41999999999", "12345678900", 1L);
        TipoUsuario tipoAntigo = new TipoUsuario(2L, "CLIENTE");
        Usuario usuarioExistente = Usuario.reconstruir(10L, "João", "joao@email.com", "41999999999", "12345678900", tipoAntigo);
        TipoUsuario tipoNovo = new TipoUsuario(1L, "ADMIN");
        Usuario usuarioAtualizado = Usuario.reconstruir(10L, "João Atualizado", "joao@email.com", "41999999999", "12345678900", tipoNovo);

        when(usuarioGateway.buscarPorId(10L)).thenReturn(Optional.of(usuarioExistente));
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(tipoNovo));
        when(usuarioGateway.salvar(any(Usuario.class))).thenReturn(usuarioAtualizado);

        // Act
        UsuarioOutput output = useCase.executar(input);

        // Assert
        assertEquals(10L, output.id());
        assertEquals("João Atualizado", output.nome());
        assertEquals("ADMIN", output.tipoUsuario());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        // Arrange
        AtualizarUsuarioInput input = new AtualizarUsuarioInput(10L, "João", "joao@email.com", "41999999999", "12345678900", 1L);

        when(usuarioGateway.buscarPorId(10L)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.executar(input));
        assertEquals("Usuário não encontrado", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoEncontrado() {
        // Arrange
        AtualizarUsuarioInput input = new AtualizarUsuarioInput(10L, "João", "joao@email.com", "41999999999", "12345678900", 1L);
        TipoUsuario tipo = new TipoUsuario(2L, "CLIENTE");
        Usuario usuarioExistente = Usuario.reconstruir(10L, "João", "joao@email.com", "41999999999", "12345678900", tipo);

        when(usuarioGateway.buscarPorId(10L)).thenReturn(Optional.of(usuarioExistente));
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.executar(input));
        assertEquals("Tipo de usuário não encontrado", exception.getMessage());
    }
}
