package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.domain.Usuario;
import com.postech.techchallenge.fase2.usuario.core.dto.UsuarioOutput;
import com.postech.techchallenge.fase2.usuario.core.gateway.UsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListarUsuariosUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private ListarUsuariosUseCase useCase;

    @Test
    void deveListarUsuariosComSucesso() {
        // Arrange
        TipoUsuario tipo = new TipoUsuario(1L, "ADMIN");
        Usuario usuario1 = Usuario.reconstruir(1L, "João", "joao@email.com", "41999999999", "12345678900", tipo);
        Usuario usuario2 = Usuario.reconstruir(2L, "Maria", "maria@email.com", "41888888888", "98765432100", tipo);

        when(usuarioGateway.listarTodos()).thenReturn(List.of(usuario1, usuario2));

        // Act
        List<UsuarioOutput> outputs = useCase.executar();

        // Assert
        assertEquals(2, outputs.size());
        assertEquals("João", outputs.get(0).nome());
        assertEquals("Maria", outputs.get(1).nome());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHaUsuarios() {
        // Arrange
        when(usuarioGateway.listarTodos()).thenReturn(List.of());

        // Act
        List<UsuarioOutput> outputs = useCase.executar();

        // Assert
        assertTrue(outputs.isEmpty());
    }
}
