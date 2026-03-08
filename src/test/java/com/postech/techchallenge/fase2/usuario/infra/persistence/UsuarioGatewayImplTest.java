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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioGatewayImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private UsuarioGatewayImpl gateway;

    @Test
    void deveSalvarUsuario() {
        // Arrange
        TipoUsuario tipo = new TipoUsuario(1L, "ADMIN");
        Usuario usuario = Usuario.criar("João", "joao@email.com", "41999999999", "12345678900", tipo);

        TipoUsuarioEntity tipoEntity = new TipoUsuarioEntity();
        tipoEntity.setId(1L);
        tipoEntity.setNome("ADMIN");

        UsuarioEntity entitySalva = new UsuarioEntity();
        entitySalva.setId(10L);
        entitySalva.setNome("João");
        entitySalva.setEmail("joao@email.com");
        entitySalva.setTelefone("41999999999");
        entitySalva.setCpf("12345678900");
        entitySalva.setTipoUsuario(tipoEntity);

        when(tipoUsuarioRepository.findById(1L)).thenReturn(Optional.of(tipoEntity));
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(entitySalva);

        // Act
        Usuario result = gateway.salvar(usuario);

        // Assert
        assertEquals(10L, result.getId());
        assertEquals("João", result.getNome());
        assertEquals("ADMIN", result.getTipoUsuario().getDescricao());
        verify(usuarioRepository).save(any(UsuarioEntity.class));
    }

    @Test
    void deveBuscarPorIdQuandoEncontrado() {
        // Arrange
        TipoUsuarioEntity tipoEntity = new TipoUsuarioEntity();
        tipoEntity.setId(1L);
        tipoEntity.setNome("ADMIN");

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(10L);
        entity.setNome("João");
        entity.setEmail("joao@email.com");
        entity.setTelefone("41999999999");
        entity.setCpf("12345678900");
        entity.setTipoUsuario(tipoEntity);

        when(usuarioRepository.findById(10L)).thenReturn(Optional.of(entity));

        // Act
        Optional<Usuario> result = gateway.buscarPorId(10L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(10L, result.get().getId());
        assertEquals("João", result.get().getNome());
    }

    @Test
    void deveBuscarPorIdQuandoNaoEncontrado() {
        // Arrange
        when(usuarioRepository.findById(10L)).thenReturn(Optional.empty());

        // Act
        Optional<Usuario> result = gateway.buscarPorId(10L);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void deveListarTodos() {
        // Arrange
        TipoUsuarioEntity tipoEntity = new TipoUsuarioEntity();
        tipoEntity.setId(1L);
        tipoEntity.setNome("ADMIN");

        UsuarioEntity entity1 = new UsuarioEntity();
        entity1.setId(1L);
        entity1.setNome("João");
        entity1.setEmail("joao@email.com");
        entity1.setTelefone("41999999999");
        entity1.setCpf("12345678900");
        entity1.setTipoUsuario(tipoEntity);

        UsuarioEntity entity2 = new UsuarioEntity();
        entity2.setId(2L);
        entity2.setNome("Maria");
        entity2.setEmail("maria@email.com");
        entity2.setTelefone("41888888888");
        entity2.setCpf("98765432100");
        entity2.setTipoUsuario(tipoEntity);

        when(usuarioRepository.findAll()).thenReturn(List.of(entity1, entity2));

        // Act
        List<Usuario> result = gateway.listarTodos();

        // Assert
        assertEquals(2, result.size());
        assertEquals("João", result.get(0).getNome());
        assertEquals("Maria", result.get(1).getNome());
    }

    @Test
    void deveDeletarUsuario() {
        // Arrange
        doNothing().when(usuarioRepository).deleteById(10L);

        // Act
        gateway.deletar(10L);

        // Assert
        verify(usuarioRepository).deleteById(10L);
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoEncontradoNoSalvar() {
        // Arrange
        TipoUsuario tipo = new TipoUsuario(1L, "ADMIN");
        Usuario usuario = Usuario.criar("João", "joao@email.com", "41999999999", "12345678900", tipo);

        when(tipoUsuarioRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> gateway.salvar(usuario));
        assertEquals("TipoUsuario não encontrado", exception.getMessage());
    }
}
