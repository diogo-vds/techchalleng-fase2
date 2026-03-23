package com.postech.techchallenge.fase2.restaurante.core.usecase;

import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeletarRestauranteUseCaseTest {

    private RestauranteGateway gateway;
    private DeletarRestauranteUseCase useCase;

    @BeforeEach
    void setup() {
        gateway = mock(RestauranteGateway.class);
        useCase = new DeletarRestauranteUseCase(gateway);
    }

    @Test
    void deveDeletarRestauranteQuandoExistir() {

        Long id = 1L;

        Restaurante restaurante = mock(Restaurante.class);

        when(gateway.buscarPorId(id)).thenReturn(Optional.of(restaurante));

        useCase.executar(id);

        verify(gateway).buscarPorId(id);
        verify(gateway).deletar(id);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExistir() {

        Long id = 1L;

        when(gateway.buscarPorId(id)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                useCase.executar(id)
        );

        assertEquals("Restaurante não encontrado", ex.getMessage());

        verify(gateway).buscarPorId(id);
        verify(gateway, never()).deletar(any());
    }
}