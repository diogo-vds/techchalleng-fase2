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
                "Pizza Antiga",
                "Descricao Antiga",
                BigDecimal.valueOf(40.0),
                true,
                "/path/to/old_photo.jpg"
        );

        CardapioInput input = new CardapioInput(
                1L,
                "Pizza Nova",
                "Descricao Nova",
                BigDecimal.valueOf(60.0),
                false,
                "/path/to/new_photo.jpg"
        );

        Cardapio cardapioAtualizado = Cardapio.reconstruir(
                1L,
                "Pizza Nova",
                "Descricao Nova",
                BigDecimal.valueOf(60.0),
                false,
                "/path/to/new_photo.jpg"
        );

        when(cardapioGateway.buscarPorId(1L)).thenReturn(Optional.of(cardapioExistente));
        when(cardapioGateway.salvar(any(Cardapio.class))).thenReturn(cardapioAtualizado);

        CardapioOutput output = useCase.executar(input);

        assertNotNull(output);
        assertEquals(1L, output.id());
        assertEquals("Pizza Nova", output.nome());
        assertEquals("Descricao Nova", output.descricao());
        assertEquals(BigDecimal.valueOf(60.0), output.preco());
        assertFalse(output.disponivelApenasRestaurante());
        assertEquals("/path/to/new_photo.jpg", output.caminhoFoto());

        verify(cardapioGateway, times(1)).buscarPorId(1L);
        verify(cardapioGateway, times(1)).salvar(any(Cardapio.class));
    }

    @Test
    void deveLancarExcecaoQuandoCardapioNaoExistir() {
        CardapioInput input = new CardapioInput(
                1L,
                "Pizza Nova",
                "Descricao Nova",
                BigDecimal.valueOf(60.0),
                false,
                "/path/to/new_photo.jpg"
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
    void deveLancarExcecaoQuandoIdNaoForInformadoNaAtualizacao() {
        CardapioInput input = new CardapioInput(
                null,
                "Pizza Nova",
                "Descricao Nova",
                BigDecimal.valueOf(60.0),
                false,
                "/path/to/new_photo.jpg"
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
