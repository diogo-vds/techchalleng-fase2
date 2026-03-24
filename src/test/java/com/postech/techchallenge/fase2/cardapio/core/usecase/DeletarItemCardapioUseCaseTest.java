package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.ItemCardapio;
import com.postech.techchallenge.fase2.cardapio.core.gateway.ItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeletarItemCardapioUseCaseTest {

    private ItemCardapioGateway gateway;
    private DeletarItemCardapioUseCase useCase;

    @BeforeEach
    void setup() {
        gateway = mock(ItemCardapioGateway.class);
        useCase = new DeletarItemCardapioUseCase(gateway);
    }

    @Test
    void deveDeletarItemComSucesso() {
        ItemCardapio item = ItemCardapio.reconstruir(
                1L, 1L, "Batata", "Desc", new BigDecimal("5.00"), true, "/foto.jpg");

        when(gateway.buscarPorId(1L)).thenReturn(Optional.of(item));
        doNothing().when(gateway).deletar(1L);

        assertDoesNotThrow(() -> useCase.executar(1L));
        verify(gateway).deletar(1L);
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoExistir() {
        when(gateway.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> useCase.executar(1L));
    }
}
