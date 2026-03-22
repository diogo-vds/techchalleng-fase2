package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListarCardapioUseCaseTest {

    private CardapioGateway cardapioGateway;
    private ListarCardapioUseCase useCase;

    @BeforeEach
    void setup() {
        cardapioGateway = mock(CardapioGateway.class);
        useCase = new ListarCardapioUseCase(cardapioGateway);
    }

    @Test
    void deveListarCardapioComSucesso() {
        Cardapio c1 = Cardapio.reconstruir(1L, "X-Salada", "Hamburguer", new BigDecimal("20.00"), false, "/f1.jpg");
        Cardapio c2 = Cardapio.reconstruir(2L, "Coca-Cola", "Refrigerante", new BigDecimal("5.00"), false, "/f2.jpg");

        when(cardapioGateway.listarTodos()).thenReturn(List.of(c1, c2));

        List<CardapioOutput> result = useCase.executar();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).id());
        assertEquals("X-Salada", result.get(0).nome());
        assertEquals(2L, result.get(1).id());
        assertEquals("Coca-Cola", result.get(1).nome());

        verify(cardapioGateway, times(1)).listarTodos();
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistirCardapio() {
        when(cardapioGateway.listarTodos()).thenReturn(List.of());

        List<CardapioOutput> result = useCase.executar();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(cardapioGateway, times(1)).listarTodos();
    }
}
