package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.ItemCardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioInput;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.ItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CriarItemCardapioUseCaseTest {

    private ItemCardapioGateway gateway;
    private CriarItemCardapioUseCase useCase;

    @BeforeEach
    void setup() {
        gateway = mock(ItemCardapioGateway.class);
        useCase = new CriarItemCardapioUseCase(gateway);
    }

    @Test
    void deveCriarItemCardapioComSucesso() {
        ItemCardapioInput input = new ItemCardapioInput(
                null,
                1L,
                "Batata",
                "Frita",
                new BigDecimal("10.00"),
                true,
                "/foto.jpg"
        );

        ItemCardapio salvo = ItemCardapio.reconstruir(
                1L,
                1L,
                "Batata",
                "Frita",
                new BigDecimal("10.00"),
                true,
                "/foto.jpg"
        );

        when(gateway.salvar(any(ItemCardapio.class))).thenReturn(salvo);

        ItemCardapioOutput output = useCase.executar(input);

        assertNotNull(output);
        assertEquals(1L, output.id());
        assertEquals("Batata", output.nome());
        verify(gateway, times(1)).salvar(any(ItemCardapio.class));
    }

    @Test
    void deveLancarExcecaoQuandoInputForNulo() {
        assertThrows(IllegalArgumentException.class, () -> useCase.executar(null));
    }

    @Test
    void deveLancarExcecaoQuandoIdForInformado() {
        ItemCardapioInput input = new ItemCardapioInput(
                1L,
                1L,
                "Batata",
                "Frita",
                new BigDecimal("10.00"),
                true,
                "/foto.jpg"
        );

        assertThrows(IllegalArgumentException.class, () -> useCase.executar(input));
    }
}
