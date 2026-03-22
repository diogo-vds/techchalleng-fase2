package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeletarCardapioUseCaseTest {

    private CardapioGateway cardapioGateway;
    private DeletarCardapioUseCase useCase;

    @BeforeEach
    void setup() {
        cardapioGateway = mock(CardapioGateway.class);
        useCase = new DeletarCardapioUseCase(cardapioGateway);
    }

    @Test
    void deveDeletarCardapioComSucesso() {
        Long id = 1L;
        Cardapio cardapio = Cardapio.reconstruir(
                1L,
                "X-Salada",
                "Hamburguer",
                new BigDecimal("20.00"),
                false,
                "/foto.jpg"
        );

        when(cardapioGateway.buscarPorId(id)).thenReturn(Optional.of(cardapio));
        doNothing().when(cardapioGateway).deletar(id);

        assertDoesNotThrow(() -> useCase.executar(id));
        verify(cardapioGateway, times(1)).buscarPorId(id);
        verify(cardapioGateway, times(1)).deletar(id);
    }

    @Test
    void deveLancarExcecaoQuandoIdForNulo() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(null)
        );

        assertEquals("Id não pode ser nulo", exception.getMessage());
        verify(cardapioGateway, never()).buscarPorId(any());
        verify(cardapioGateway, never()).deletar(any());
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
        verify(cardapioGateway, never()).deletar(any());
    }
}
