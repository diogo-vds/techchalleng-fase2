package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.ItemCardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.ItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListarItemCardapioUseCaseTest {

    private ItemCardapioGateway gateway;
    private ListarItemCardapioUseCase useCase;

    @BeforeEach
    void setup() {
        gateway = mock(ItemCardapioGateway.class);
        useCase = new ListarItemCardapioUseCase(gateway);
    }

    @Test
    void deveListarItens() {
        ItemCardapio item1 = ItemCardapio.reconstruir(
                1L, 1L, "Batata", "Desc", new BigDecimal("5.00"), true, "/foto.jpg");
        ItemCardapio item2 = ItemCardapio.reconstruir(
                2L, 1L, "Refri", "Desc", new BigDecimal("5.00"), true, "/foto.jpg");

        when(gateway.listarTodos()).thenReturn(List.of(item1, item2));

        List<ItemCardapioOutput> output = useCase.executar();

        assertNotNull(output);
        assertEquals(2, output.size());
    }
}
