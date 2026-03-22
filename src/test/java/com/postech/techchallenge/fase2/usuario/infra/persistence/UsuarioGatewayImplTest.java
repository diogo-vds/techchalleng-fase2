package com.postech.techchallenge.fase2.usuario.infra.persistence;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.domain.Usuario;
import com.postech.techchallenge.fase2.usuario.infra.persistence.entity.TipoUsuarioEntity;
import com.postech.techchallenge.fase2.usuario.infra.persistence.entity.UsuarioEntity;
import com.postech.techchallenge.fase2.usuario.infra.persistence.repository.TipoUsuarioRepository;
import com.postech.techchallenge.fase2.usuario.infra.persistence.repository.UsuarioRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioGatewayImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private UsuarioGatewayImpl gateway;

    private Usuario criarUsuario() {
        TipoUsuario tipo = new TipoUsuario(1L, "ADMIN");

        return Usuario.reconstruir(
                1L,
                "Joao",
                "joao@email.com",
                "123456789",
                "12345678900",
                tipo
        );
    }

    private UsuarioEntity criarUsuarioEntity() {

        TipoUsuarioEntity tipo = new TipoUsuarioEntity();
        tipo.setId(1L);
        tipo.setNome("ADMIN");

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(1L);
        entity.setNome("Joao");
        entity.setEmail("joao@email.com");
        entity.setTelefone("123456789");
        entity.setCpf("12345678900");
        entity.setTipoUsuario(tipo);

        return entity;
    }

    @Test
    void deveSalvarUsuario() {

        Usuario usuario = criarUsuario();
        UsuarioEntity entity = criarUsuarioEntity();

        when(tipoUsuarioRepository.findById(1L))
                .thenReturn(Optional.of(entity.getTipoUsuario()));

        when(usuarioRepository.save(any()))
                .thenReturn(entity);

        Usuario resultado = gateway.salvar(usuario);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());

        verify(usuarioRepository).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoExisteAoSalvar() {
        Usuario usuario = criarUsuario();

        when(tipoUsuarioRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> gateway.salvar(usuario));

        assertEquals("TipoUsuario n\u00e3o encontrado", exception.getMessage());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void deveListarUsuarios() {

        UsuarioEntity entity = criarUsuarioEntity();

        when(usuarioRepository.findAll())
                .thenReturn(List.of(entity));

        List<Usuario> lista = gateway.listarTodos();

        assertEquals(1, lista.size());
        assertEquals(1L, lista.get(0).getId());
    }

    @Test
    void deveBuscarUsuarioPorId() {

        UsuarioEntity entity = criarUsuarioEntity();

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(entity));

        Optional<Usuario> resultado = gateway.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }

    @Test
    void deveRetornarVazioQuandoUsuarioNaoExiste() {

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.empty());

        Optional<Usuario> resultado = gateway.buscarPorId(1L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveDeletarUsuario() {

        gateway.deletar(1L);

        verify(usuarioRepository).deleteById(1L);
    }
}
