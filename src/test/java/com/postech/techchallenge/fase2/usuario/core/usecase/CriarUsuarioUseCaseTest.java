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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @InjectMocks
    private CriarUsuarioUseCase useCase;

    @Test
    void deveCriarUsuarioComSucesso() {
        CriarUsuarioInput input = new CriarUsuarioInput("Joao", "joao@email.com", "41999999999", "12345678900", 1L);
        TipoUsuario tipo = new TipoUsuario(1L, "ADMIN");
        Usuario usuarioSalvo = Usuario.reconstruir(10L, "Joao", "joao@email.com", "41999999999", "12345678900", tipo);

        when(usuarioGateway.buscarPorEmail("joao@email.com")).thenReturn(Optional.empty());
        when(usuarioGateway.buscarPorTelefone("41999999999")).thenReturn(Optional.empty());
        when(usuarioGateway.buscarPorCpf("12345678900")).thenReturn(Optional.empty());
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(tipo));
        when(usuarioGateway.salvar(any(Usuario.class))).thenReturn(usuarioSalvo);

        UsuarioOutput output = useCase.executar(input);

        assertEquals(10L, output.id());
        assertEquals("Joao", output.nome());
        assertEquals("joao@email.com", output.email());
        assertEquals("41999999999", output.telefone());
        assertEquals("12345678900", output.cpf());
        assertEquals("ADMIN", output.tipoUsuario());
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExistir() {
        CriarUsuarioInput input = new CriarUsuarioInput("Joao", "joao@email.com", "41999999999", "12345678900", 1L);

        when(usuarioGateway.buscarPorEmail("joao@email.com"))
                .thenReturn(Optional.of(Usuario.reconstruir(
                        1L, "Maria", "joao@email.com", "41888888888", "99999999999", new TipoUsuario(2L, "CLIENTE")
                )));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.executar(input));

        assertEquals("Email 'joao@email.com' ja cadastrado", exception.getMessage());
        verify(usuarioGateway, never()).salvar(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoTelefoneJaExistir() {
        CriarUsuarioInput input = new CriarUsuarioInput("Joao", "joao@email.com", "41999999999", "12345678900", 1L);

        when(usuarioGateway.buscarPorEmail("joao@email.com")).thenReturn(Optional.empty());
        when(usuarioGateway.buscarPorTelefone("41999999999"))
                .thenReturn(Optional.of(Usuario.reconstruir(
                        1L, "Maria", "maria@email.com", "41999999999", "99999999999", new TipoUsuario(2L, "CLIENTE")
                )));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.executar(input));

        assertEquals("Telefone '41999999999' ja cadastrado", exception.getMessage());
        verify(usuarioGateway, never()).salvar(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoCpfJaExistir() {
        CriarUsuarioInput input = new CriarUsuarioInput("Joao", "joao@email.com", "41999999999", "12345678900", 1L);

        when(usuarioGateway.buscarPorEmail("joao@email.com")).thenReturn(Optional.empty());
        when(usuarioGateway.buscarPorTelefone("41999999999")).thenReturn(Optional.empty());
        when(usuarioGateway.buscarPorCpf("12345678900"))
                .thenReturn(Optional.of(Usuario.reconstruir(
                        1L, "Maria", "maria@email.com", "41888888888", "12345678900", new TipoUsuario(2L, "CLIENTE")
                )));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.executar(input));

        assertEquals("CPF '12345678900' ja cadastrado", exception.getMessage());
        verify(usuarioGateway, never()).salvar(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoEncontrado() {
        CriarUsuarioInput input = new CriarUsuarioInput("Joao", "joao@email.com", "41999999999", "12345678900", 1L);

        when(usuarioGateway.buscarPorEmail("joao@email.com")).thenReturn(Optional.empty());
        when(usuarioGateway.buscarPorTelefone("41999999999")).thenReturn(Optional.empty());
        when(usuarioGateway.buscarPorCpf("12345678900")).thenReturn(Optional.empty());
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.executar(input));

        assertEquals("Tipo de usuario nao encontrado", exception.getMessage());
    }
}
