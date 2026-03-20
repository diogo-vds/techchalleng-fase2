package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarCardapioPorIdUseCaseTest {

    private CardapioGateway cardapioGateway;
    private BuscarCardapioPorIdUseCase useCase;

    @BeforeEach
    void setup() {
        cardapioGateway = mock(CardapioGateway.class);
        useCase = new BuscarCardapioPorIdUseCase(cardapioGateway);
    }

    @Test
    void deveBuscarCardapioPorIdComSucesso() {
        Long id = 1L;
        Cardapio cardapio = Cardapio.reconstruir(
                1L,
                "Pizza",
                "Pizza de Calabresa",
                BigDecimal.valueOf(50.0),
                false,
                "/path/to/photo.jpg"
        );

        when(cardapioGateway.buscarPorId(id)).thenReturn(Optional.of(cardapio));

        CardapioOutput resultado = useCase.executar(id);

        assertNotNull(resultado);
        assertEquals(1L, resultado.id());
        assertEquals("Pizza", resultado.nome());

        verify(cardapioGateway, times(1)).buscarPorId(id);
    }

    @Test
    void deveLancarExcecaoQuandoIdForNulo() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(null)
        );

        assertEquals("Id não pode ser nulo", exception.getMessage());
        verify(cardapioGateway, never()).buscarPorId(any());
    }

    @Test
    void deveLancarExcecaoQuandoCardapioNaoExistir() {
        Long id = 1L;
        when(cardapioGateway.buscarPorId(id)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(id)
        );

        assertEquals("Cardapio não encontrado", exception.getMessage());
        verify(cardapioGateway, times(1)).buscarPorId(id);
    }
}
