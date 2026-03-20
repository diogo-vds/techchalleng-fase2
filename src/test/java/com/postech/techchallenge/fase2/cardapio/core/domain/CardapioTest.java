package com.postech.techchallenge.fase2.cardapio.core.domain;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class CardapioTest {

    @Test
    void deveCriarCardapioValido() {
        Cardapio cardapio = Cardapio.criar(
                "Pizza",
                "Pizza de Calabresa",
                BigDecimal.valueOf(50.0),
                false,
                "/path/to/photo.jpg"
        );

        assertNull(cardapio.getId());
        assertEquals("Pizza", cardapio.getNome());
        assertEquals("Pizza de Calabresa", cardapio.getDescricao());
        assertEquals(BigDecimal.valueOf(50.0), cardapio.getPreco());
        assertFalse(cardapio.getDisponivelApenasRestaurante());
        assertEquals("/path/to/photo.jpg", cardapio.getCaminhoFoto());
    }

    @Test
    void deveReconstruirCardapioComId() {
        Cardapio cardapio = Cardapio.reconstruir(
                1L,
                "Pizza",
                "Pizza de Calabresa",
                BigDecimal.valueOf(50.0),
                false,
                "/path/to/photo.jpg"
        );

        assertEquals(1L, cardapio.getId());
        assertEquals("Pizza", cardapio.getNome());
    }

    @Test
    void naoDeveReconstruirSemId() {
        assertThrows(IllegalArgumentException.class, () ->
                Cardapio.reconstruir(
                        null,
                        "Pizza",
                        "Pizza de Calabresa",
                        BigDecimal.valueOf(50.0),
                        false,
                        "/path/to/photo.jpg"
                )
        );
    }

    @Test
    void naoDeveAceitarPrecoNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
                Cardapio.criar(
                        "Pizza",
                        "Pizza de Calabresa",
                        BigDecimal.valueOf(-10.0),
                        false,
                        "/path/to/photo.jpg"
                )
        );
    }

    @Test
    void naoDeveAceitarNomeVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                Cardapio.criar(
                        "",
                        "Pizza de Calabresa",
                        BigDecimal.valueOf(50.0),
                        false,
                        "/path/to/photo.jpg"
                )
        );
    }

    @Test
    void naoDeveAceitarDescricaoVazia() {
        assertThrows(IllegalArgumentException.class, () ->
                Cardapio.criar(
                        "Pizza",
                        "",
                        BigDecimal.valueOf(50.0),
                        false,
                        "/path/to/photo.jpg"
                )
        );
    }

    @Test
    void naoDeveAceitarCaminhoFotoVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                Cardapio.criar(
                        "Pizza",
                        "Pizza de Calabresa",
                        BigDecimal.valueOf(50.0),
                        false,
                        ""
                )
        );
    }
}
