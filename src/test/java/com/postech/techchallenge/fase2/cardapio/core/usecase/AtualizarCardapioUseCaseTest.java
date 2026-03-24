package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioInput;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
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
                Collections.emptyList()
        );

        CardapioInput input = new CardapioInput(
                1L,
                "Novo",
                "Descricao Nova",
                Collections.emptyList()
        );

        Cardapio cardapioAtualizado = Cardapio.reconstruir(
                1L,
                "Novo",
                "Descricao Nova",
                Collections.emptyList()
        );

        when(cardapioGateway.buscarPorId(1L)).thenReturn(Optional.of(cardapioExistente));
        when(cardapioGateway.salvar(any(Cardapio.class))).thenReturn(cardapioAtualizado);

        CardapioOutput output = useCase.executar(input);

        assertNotNull(output);
        assertEquals(1L, output.id());
        assertEquals("Novo", output.nome());
        assertEquals("Descricao Nova", output.descricao());

        verify(cardapioGateway, times(1)).buscarPorId(1L);
        verify(cardapioGateway, times(1)).salvar(any(Cardapio.class));
    }

    @Test
    void deveLancarExcecaoQuandoCardapioNaoExistir() {
        CardapioInput input = new CardapioInput(
                1L,
                "Novo",
                "Descricao Nova",
                Collections.emptyList()
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
                Collections.emptyList()
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
