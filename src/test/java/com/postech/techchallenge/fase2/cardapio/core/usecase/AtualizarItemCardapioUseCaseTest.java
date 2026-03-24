package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.ItemCardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioInput;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.ItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AtualizarItemCardapioUseCaseTest {

    private ItemCardapioGateway gateway;
    private AtualizarItemCardapioUseCase useCase;

    @BeforeEach
    void setup() {
        gateway = mock(ItemCardapioGateway.class);
        useCase = new AtualizarItemCardapioUseCase(gateway);
    }

    @Test
    void deveAtualizarItemCardapioComSucesso() {
        ItemCardapio existente = ItemCardapio.reconstruir(
                1L, 1L, "Antigo", "Desc", new BigDecimal("5.00"), true, "/foto.jpg");

        ItemCardapioInput input = new ItemCardapioInput(
                1L, 1L, "Novo", "Desc Nova", new BigDecimal("10.00"), false, "/nova.jpg");

        ItemCardapio atualizado = ItemCardapio.reconstruir(
                1L, 1L, "Novo", "Desc Nova", new BigDecimal("10.00"), false, "/nova.jpg");

        when(gateway.buscarPorId(1L)).thenReturn(Optional.of(existente));
        when(gateway.salvar(any(ItemCardapio.class))).thenReturn(atualizado);

        ItemCardapioOutput output = useCase.executar(input);

        assertNotNull(output);
        assertEquals("Novo", output.nome());
        verify(gateway).salvar(any(ItemCardapio.class));
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoExistir() {
        ItemCardapioInput input = new ItemCardapioInput(
                1L, 1L, "Novo", "Desc Nova", new BigDecimal("10.00"), false, "/nova.jpg");

        when(gateway.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> useCase.executar(input));
    }
}
