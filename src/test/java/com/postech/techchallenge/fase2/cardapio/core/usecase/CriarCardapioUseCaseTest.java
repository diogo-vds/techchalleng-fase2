package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioInput;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CriarCardapioUseCaseTest {

    private CardapioGateway cardapioGateway;
    private CriarCardapioUseCase useCase;

    @BeforeEach
    void setup() {
        cardapioGateway = mock(CardapioGateway.class);
        useCase = new CriarCardapioUseCase(cardapioGateway);
    }

    @Test
    void deveCriarCardapioComSucesso() {
        CardapioInput input = new CardapioInput(
                null,
                "X-Salada",
                "Hamburguer com salada",
                new BigDecimal("20.00"),
                false,
                "/fotos/xsalada.jpg"
        );

        Cardapio cardapioSalvo = Cardapio.reconstruir(
                1L,
                "X-Salada",
                "Hamburguer com salada",
                new BigDecimal("20.00"),
                false,
                "/fotos/xsalada.jpg"
        );

        when(cardapioGateway.salvar(any(Cardapio.class))).thenReturn(cardapioSalvo);

        CardapioOutput output = useCase.executar(input);

        assertNotNull(output);
        assertEquals(1L, output.id());
        assertEquals("X-Salada", output.nome());
        assertEquals(new BigDecimal("20.00"), output.preco());
        verify(cardapioGateway, times(1)).salvar(any(Cardapio.class));
    }

    @Test
    void deveLancarExcecaoQuandoInputForNulo() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(null)
        );

        assertEquals("Input não pode ser nulo", exception.getMessage());
        verify(cardapioGateway, never()).salvar(any());
    }

    @Test
    void deveLancarExcecaoQuandoIdForInformadoNaCriacao() {
        CardapioInput input = new CardapioInput(
                1L,
                "X-Salada",
                "Hamburguer com salada",
                new BigDecimal("20.00"),
                false,
                "/fotos/xsalada.jpg"
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(input)
        );

        assertEquals("Id não deve ser informado para criação", exception.getMessage());
        verify(cardapioGateway, never()).salvar(any());
    }
}
