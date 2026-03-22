package com.postech.techchallenge.fase2.cardapio.core.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CardapioTest {

    @Test
    void deveCriarCardapioValido() {
        Cardapio cardapio = Cardapio.criar(
                "Hamburguer",
                "Delicioso hamburguer artesanal",
                new BigDecimal("25.00"),
                true,
                "/fotos/hamburguer.jpg"
        );

        assertNull(cardapio.getId());
        assertEquals("Hamburguer", cardapio.getNome());
        assertEquals("Delicioso hamburguer artesanal", cardapio.getDescricao());
        assertEquals(new BigDecimal("25.00"), cardapio.getPreco());
        assertTrue(cardapio.getDisponivelApenasRestaurante());
        assertEquals("/fotos/hamburguer.jpg", cardapio.getCaminhoFoto());
    }

    @Test
    void deveReconstruirCardapioComId() {
        Cardapio cardapio = Cardapio.reconstruir(
                1L,
                "Pizza",
                "Pizza de Calabresa",
                new BigDecimal("40.00"),
                false,
                "/fotos/pizza.jpg"
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
                        new BigDecimal("40.00"),
                        false,
                        "/fotos/pizza.jpg"
                )
        );
    }

    @Test
    void naoDeveAceitarPrecoNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
                Cardapio.criar(
                        "Hamburguer",
                        "Descricao",
                        new BigDecimal("-10.00"),
                        true,
                        "/foto.jpg"
                )
        );
    }

    @Test
    void naoDeveAceitarNomeVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                Cardapio.criar(
                        "",
                        "Descricao",
                        new BigDecimal("10.00"),
                        true,
                        "/foto.jpg"
                )
        );
    }

    @Test
    void naoDeveAceitarDescricaoVazia() {
        assertThrows(IllegalArgumentException.class, () ->
                Cardapio.criar(
                        "Nome",
                        "",
                        new BigDecimal("10.00"),
                        true,
                        "/foto.jpg"
                )
        );
    }

    @Test
    void naoDeveAceitarCaminhoFotoVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                Cardapio.criar(
                        "Nome",
                        "Descricao",
                        new BigDecimal("10.00"),
                        true,
                        ""
                )
        );
    }

    @Test
    void naoDeveAceitarNomeNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                Cardapio.criar(
                        null,
                        "Descricao",
                        new BigDecimal("10.00"),
                        true,
                        "/foto.jpg"
                )
        );
    }

    @Test
    void naoDeveAceitarPrecoNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                Cardapio.criar(
                        "Nome",
                        "Descricao",
                        null,
                        true,
                        "/foto.jpg"
                )
        );
    }
}
