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
        Cardapio cardapio1 = Cardapio.reconstruir(
                1L,
                "Pizza",
                "Pizza de Calabresa",
                BigDecimal.valueOf(50.0),
                false,
                "/path/to/photo1.jpg"
        );
        Cardapio cardapio2 = Cardapio.reconstruir(
                2L,
                "Hamburguer",
                "Hamburguer com bacon",
                BigDecimal.valueOf(35.0),
                false,
                "/path/to/photo2.jpg"
        );

        when(cardapioGateway.listarTodos()).thenReturn(List.of(cardapio1, cardapio2));

        List<CardapioOutput> resultado = useCase.executar();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        CardapioOutput primeiro = resultado.get(0);
        assertEquals(1L, primeiro.id());
        assertEquals("Pizza", primeiro.nome());

        verify(cardapioGateway, times(1)).listarTodos();
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremItensNoCardapio() {
        when(cardapioGateway.listarTodos()).thenReturn(List.of());

        List<CardapioOutput> resultado = useCase.executar();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

        verify(cardapioGateway, times(1)).listarTodos();
    }
}
