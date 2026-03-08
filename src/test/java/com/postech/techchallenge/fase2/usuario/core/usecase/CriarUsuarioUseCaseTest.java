package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.domain.Usuario;
import com.postech.techchallenge.fase2.usuario.core.dto.CriarUsuarioInput;
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
public class CriarUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @InjectMocks
    private CriarUsuarioUseCase useCase;

    @Test
    void deveCriarUsuarioComSucesso() {
        // Arrange
        CriarUsuarioInput input = new CriarUsuarioInput("João", "joao@email.com", "41999999999", "12345678900", 1L);
        TipoUsuario tipo = new TipoUsuario(1L, "ADMIN");
        Usuario usuarioSalvo = Usuario.reconstruir(10L, "João", "joao@email.com", "41999999999", "12345678900", tipo);

        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(tipo));
        when(usuarioGateway.salvar(any(Usuario.class))).thenReturn(usuarioSalvo);

        // Act
        UsuarioOutput output = useCase.executar(input);

        // Assert
        assertEquals(10L, output.id());
        assertEquals("João", output.nome());
        assertEquals("joao@email.com", output.email());
        assertEquals("41999999999", output.telefone());
        assertEquals("12345678900", output.cpf());
        assertEquals("ADMIN", output.tipoUsuario());
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoEncontrado() {
        // Arrange
        CriarUsuarioInput input = new CriarUsuarioInput("João", "joao@email.com", "41999999999", "12345678900", 1L);

        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.executar(input));
        assertEquals("Tipo de usuário não encontrado", exception.getMessage());
    }
}
