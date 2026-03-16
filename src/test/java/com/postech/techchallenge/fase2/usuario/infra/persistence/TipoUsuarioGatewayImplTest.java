package com.postech.techchallenge.fase2.usuario.infra.persistence;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.infra.persistence.entity.TipoUsuarioEntity;
import com.postech.techchallenge.fase2.usuario.infra.persistence.repository.TipoUsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
}