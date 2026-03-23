package com.postech.techchallenge.fase2.restaurante.core.usecase;

import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecuperarRestauranteUseCaseTest {

    private RestauranteGateway gateway;
    private RecuperarRestauranteUseCase useCase;

    @BeforeEach
    void setup() {
        gateway = mock(RestauranteGateway.class);
        useCase = new RecuperarRestauranteUseCase(gateway);
    }

    @Test
    void deveRetornarRestauranteQuandoIdValido() {

        Long id = 1L;
        Restaurante restaurante = mock(Restaurante.class);

        when(gateway.buscarPorId(id)).thenReturn(Optional.of(restaurante));

        Restaurante resultado = useCase.porId(id);

        assertEquals(restaurante, resultado);

        verify(gateway).buscarPorId(id);
    }

    @Test
    void deveLancarExcecaoQuandoIdForNulo() {

        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                useCase.porId(null)
        );

        assertEquals("ID do restaurante é obrigatório", ex.getMessage());

        verify(gateway, never()).buscarPorId(any());
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {

        Long id = 1L;

        when(gateway.buscarPorId(id)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                useCase.porId(id)
        );

        assertEquals("Restaurante não encontrado com ID: " + id, ex.getMessage());

        verify(gateway).buscarPorId(id);
    }

    @Test
    void deveRetornarListaDeRestaurantes() {

        List<Restaurante> lista = List.of(
                mock(Restaurante.class),
                mock(Restaurante.class)
        );

        when(gateway.listarTodos()).thenReturn(lista);

        List<Restaurante> resultado = useCase.todos();

        assertEquals(2, resultado.size());
        assertEquals(lista, resultado);

        verify(gateway).listarTodos();
    }
}