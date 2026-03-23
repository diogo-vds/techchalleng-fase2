package com.postech.techchallenge.fase2.usuario.infra.persistence;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.infra.persistence.entity.TipoUsuarioEntity;
import com.postech.techchallenge.fase2.usuario.infra.persistence.repository.TipoUsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TipoUsuarioGatewayImplTest {

    private TipoUsuarioRepository tipoUsuarioRepository;
    private TipoUsuarioGatewayImpl gateway;

    @BeforeEach
    void setup() {
        tipoUsuarioRepository = Mockito.mock(TipoUsuarioRepository.class);
        gateway = new TipoUsuarioGatewayImpl(tipoUsuarioRepository);
    }

    @Test
    void deveRetornarTipoUsuarioQuandoEncontrado() {

        TipoUsuarioEntity entity = new TipoUsuarioEntity();
        entity.setId(1L);
        entity.setNome("ADMIN");

        when(tipoUsuarioRepository.findById(1L))
                .thenReturn(Optional.of(entity));

        Optional<TipoUsuario> resultado = gateway.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        assertEquals("ADMIN", resultado.get().getDescricao());

        verify(tipoUsuarioRepository, times(1)).findById(1L);
    }

    @Test
    void deveRetornarOptionalVazioQuandoNaoEncontrado() {

        when(tipoUsuarioRepository.findById(1L))
                .thenReturn(Optional.empty());

        Optional<TipoUsuario> resultado = gateway.buscarPorId(1L);

        assertTrue(resultado.isEmpty());

        verify(tipoUsuarioRepository, times(1)).findById(1L);
    }

    @Test
    void deveBuscarPorDescricao() {
        TipoUsuarioEntity entity = new TipoUsuarioEntity();
        entity.setId(1L);
        entity.setNome("CLIENTE");

        when(tipoUsuarioRepository.findByNomeIgnoreCase("cliente"))
                .thenReturn(Optional.of(entity));

        Optional<TipoUsuario> resultado = gateway.buscarPorDescricao("cliente");

        assertTrue(resultado.isPresent());
        assertEquals("CLIENTE", resultado.get().getDescricao());
        verify(tipoUsuarioRepository, times(1)).findByNomeIgnoreCase("cliente");
    }

    @Test
    void deveSalvarTipoUsuario() {
        TipoUsuarioEntity entity = new TipoUsuarioEntity();
        entity.setId(1L);
        entity.setNome("CLIENTE");

        when(tipoUsuarioRepository.save(any(TipoUsuarioEntity.class)))
                .thenReturn(entity);

        TipoUsuario resultado = gateway.salvar(new TipoUsuario(null, "CLIENTE"));

        assertEquals(1L, resultado.getId());
        assertEquals("CLIENTE", resultado.getDescricao());
        verify(tipoUsuarioRepository, times(1)).save(any(TipoUsuarioEntity.class));
    }

    @Test
    void deveListarTodosOsTiposUsuario() {
        TipoUsuarioEntity cliente = new TipoUsuarioEntity();
        cliente.setId(1L);
        cliente.setNome("CLIENTE");

        TipoUsuarioEntity dono = new TipoUsuarioEntity();
        dono.setId(2L);
        dono.setNome("DONO_RESTAURANTE");

        when(tipoUsuarioRepository.findAll())
                .thenReturn(List.of(cliente, dono));

        List<TipoUsuario> resultado = gateway.listarTodos();

        assertEquals(2, resultado.size());
        assertEquals("CLIENTE", resultado.get(0).getDescricao());
        assertEquals("DONO_RESTAURANTE", resultado.get(1).getDescricao());
        verify(tipoUsuarioRepository, times(1)).findAll();
    }

    @Test
    void deveDeletarTipoUsuario() {
        gateway.deletar(1L);

        verify(tipoUsuarioRepository, times(1)).deleteById(1L);
    }
}
