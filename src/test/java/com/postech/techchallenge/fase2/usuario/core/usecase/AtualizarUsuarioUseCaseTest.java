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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizarUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @InjectMocks
    private AtualizarUsuarioUseCase useCase;

    @Test
    void deveAtualizarUsuarioComSucesso() {
        AtualizarUsuarioInput input = new AtualizarUsuarioInput(10L, "Joao Atualizado", "joao@email.com", "41999999999", "12345678900", 1L);
        TipoUsuario tipoAntigo = new TipoUsuario(2L, "CLIENTE");
        Usuario usuarioExistente = Usuario.reconstruir(10L, "Joao", "joao@email.com", "41999999999", "12345678900", tipoAntigo);
        TipoUsuario tipoNovo = new TipoUsuario(1L, "ADMIN");
        Usuario usuarioAtualizado = Usuario.reconstruir(10L, "Joao Atualizado", "joao@email.com", "41999999999", "12345678900", tipoNovo);

        when(usuarioGateway.buscarPorId(10L)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioGateway.buscarPorEmail("joao@email.com")).thenReturn(Optional.of(usuarioExistente));
        when(usuarioGateway.buscarPorTelefone("41999999999")).thenReturn(Optional.of(usuarioExistente));
        when(usuarioGateway.buscarPorCpf("12345678900")).thenReturn(Optional.of(usuarioExistente));
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(tipoNovo));
        when(usuarioGateway.salvar(any(Usuario.class))).thenReturn(usuarioAtualizado);

        UsuarioOutput output = useCase.executar(input);

        assertEquals(10L, output.id());
        assertEquals("Joao Atualizado", output.nome());
        assertEquals("ADMIN", output.tipoUsuario());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        AtualizarUsuarioInput input = new AtualizarUsuarioInput(10L, "Joao", "joao@email.com", "41999999999", "12345678900", 1L);

        when(usuarioGateway.buscarPorId(10L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.executar(input));
        assertEquals("Usuario nao encontrado", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExistirParaOutroUsuario() {
        AtualizarUsuarioInput input = new AtualizarUsuarioInput(10L, "Joao", "joao@email.com", "41999999999", "12345678900", 1L);
        Usuario usuarioExistente = Usuario.reconstruir(10L, "Joao", "joao@email.com", "41999999999", "12345678900", new TipoUsuario(2L, "CLIENTE"));
        Usuario usuarioComMesmoEmail = Usuario.reconstruir(11L, "Maria", "joao@email.com", "41888888888", "99999999999", new TipoUsuario(2L, "CLIENTE"));

        when(usuarioGateway.buscarPorId(10L)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioGateway.buscarPorEmail("joao@email.com")).thenReturn(Optional.of(usuarioComMesmoEmail));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.executar(input));

        assertEquals("Email 'joao@email.com' ja cadastrado", exception.getMessage());
        verify(usuarioGateway, never()).salvar(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoTelefoneJaExistirParaOutroUsuario() {
        AtualizarUsuarioInput input = new AtualizarUsuarioInput(10L, "Joao", "joao@email.com", "41999999999", "12345678900", 1L);
        Usuario usuarioExistente = Usuario.reconstruir(10L, "Joao", "joao@email.com", "41999999999", "12345678900", new TipoUsuario(2L, "CLIENTE"));
        Usuario usuarioComMesmoTelefone = Usuario.reconstruir(11L, "Maria", "maria@email.com", "41999999999", "99999999999", new TipoUsuario(2L, "CLIENTE"));

        when(usuarioGateway.buscarPorId(10L)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioGateway.buscarPorEmail("joao@email.com")).thenReturn(Optional.of(usuarioExistente));
        when(usuarioGateway.buscarPorTelefone("41999999999")).thenReturn(Optional.of(usuarioComMesmoTelefone));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.executar(input));

        assertEquals("Telefone '41999999999' ja cadastrado", exception.getMessage());
        verify(usuarioGateway, never()).salvar(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoCpfJaExistirParaOutroUsuario() {
        AtualizarUsuarioInput input = new AtualizarUsuarioInput(10L, "Joao", "joao@email.com", "41999999999", "12345678900", 1L);
        Usuario usuarioExistente = Usuario.reconstruir(10L, "Joao", "joao@email.com", "41999999999", "12345678900", new TipoUsuario(2L, "CLIENTE"));
        Usuario usuarioComMesmoCpf = Usuario.reconstruir(11L, "Maria", "maria@email.com", "41888888888", "12345678900", new TipoUsuario(2L, "CLIENTE"));

        when(usuarioGateway.buscarPorId(10L)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioGateway.buscarPorEmail("joao@email.com")).thenReturn(Optional.of(usuarioExistente));
        when(usuarioGateway.buscarPorTelefone("41999999999")).thenReturn(Optional.of(usuarioExistente));
        when(usuarioGateway.buscarPorCpf("12345678900")).thenReturn(Optional.of(usuarioComMesmoCpf));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.executar(input));

        assertEquals("CPF '12345678900' ja cadastrado", exception.getMessage());
        verify(usuarioGateway, never()).salvar(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoEncontrado() {
        AtualizarUsuarioInput input = new AtualizarUsuarioInput(10L, "Joao", "joao@email.com", "41999999999", "12345678900", 1L);
        Usuario usuarioExistente = Usuario.reconstruir(10L, "Joao", "joao@email.com", "41999999999", "12345678900", new TipoUsuario(2L, "CLIENTE"));

        when(usuarioGateway.buscarPorId(10L)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioGateway.buscarPorEmail("joao@email.com")).thenReturn(Optional.of(usuarioExistente));
        when(usuarioGateway.buscarPorTelefone("41999999999")).thenReturn(Optional.of(usuarioExistente));
        when(usuarioGateway.buscarPorCpf("12345678900")).thenReturn(Optional.of(usuarioExistente));
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.executar(input));
        assertEquals("Tipo de usuario nao encontrado", exception.getMessage());
    }
}
