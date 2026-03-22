package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioInput;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AtualizarCardapioUseCaseTest {

    private CardapioGateway cardapioGateway;
    private AtualizarCardapioUseCase useCase;

    @BeforeEach
    void setup() {
        cardapioGateway = Mockito.mock(CardapioGateway.class);
        useCase = new AtualizarCardapioUseCase(cardapioGateway);
    }

    @Test
    void deveAtualizarCardapioComSucesso() {
        Cardapio cardapioExistente = Cardapio.reconstruir(
                1L,
                "Antigo",
                "Descricao Antiga",
                new BigDecimal("10.00"),
                false,
                "/foto.jpg"
        );

        CardapioInput input = new CardapioInput(
                1L,
                "Novo",
                "Descricao Nova",
                new BigDecimal("15.00"),
                true,
                "/nova_foto.jpg"
        );

        Cardapio cardapioAtualizado = Cardapio.reconstruir(
                1L,
                "Novo",
                "Descricao Nova",
                new BigDecimal("15.00"),
                true,
                "/nova_foto.jpg"
        );

        when(cardapioGateway.buscarPorId(1L)).thenReturn(Optional.of(cardapioExistente));
        when(cardapioGateway.salvar(any(Cardapio.class))).thenReturn(cardapioAtualizado);

        CardapioOutput output = useCase.executar(input);

        assertNotNull(output);
        assertEquals(1L, output.id());
        assertEquals("Novo", output.nome());
        assertEquals("Descricao Nova", output.descricao());
        assertEquals(new BigDecimal("15.00"), output.preco());

        verify(cardapioGateway, times(1)).buscarPorId(1L);
        verify(cardapioGateway, times(1)).salvar(any(Cardapio.class));
    }

    @Test
    void deveLancarExcecaoQuandoCardapioNaoExistir() {
        CardapioInput input = new CardapioInput(
                1L,
                "Novo",
                "Descricao Nova",
                new BigDecimal("15.00"),
                true,
                "/nova_foto.jpg"
        );

        when(cardapioGateway.buscarPorId(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(input)
        );

        assertEquals("Cardapio não encontrado", exception.getMessage());
        verify(cardapioGateway, times(1)).buscarPorId(1L);
        verify(cardapioGateway, never()).salvar(any());
    }

    @Test
    void deveLancarExcecaoQuandoInputForNulo() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(null)
        );

        assertEquals("Input não pode ser nulo", exception.getMessage());
        verify(cardapioGateway, never()).buscarPorId(any());
        verify(cardapioGateway, never()).salvar(any());
    }

    @Test
    void deveLancarExcecaoQuandoIdForNulo() {
        CardapioInput input = new CardapioInput(
                null,
                "Novo",
                "Descricao Nova",
                new BigDecimal("15.00"),
                true,
                "/nova_foto.jpg"
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(input)
        );

        assertEquals("Id é obrigatório para atualização", exception.getMessage());
        verify(cardapioGateway, never()).buscarPorId(any());
        verify(cardapioGateway, never()).salvar(any());
    }
}
