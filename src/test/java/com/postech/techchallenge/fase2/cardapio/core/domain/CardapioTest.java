package com.postech.techchallenge.fase2.cardapio.core.domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CardapioTest {

    @Test
    void deveCriarCardapioValido() {
        Cardapio cardapio = Cardapio.criar(
                "Hamburguer",
                "Delicioso hamburguer artesanal",
                Collections.emptyList()
        );

        assertNull(cardapio.getId());
        assertEquals("Hamburguer", cardapio.getNome());
        assertEquals("Delicioso hamburguer artesanal", cardapio.getDescricao());
        assertEquals(Collections.emptyList(), cardapio.getItens());
    }

    @Test
    void deveReconstruirCardapioComId() {
        Cardapio cardapio = Cardapio.reconstruir(
                1L,
                "Pizza",
                "Pizza de Calabresa",
                Collections.emptyList()
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
                        Collections.emptyList()
                )
        );
    }

    @Test
    void naoDeveAceitarNomeVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                Cardapio.criar(
                        "",
                        "Descricao",
                        Collections.emptyList()
                )
        );
    }

    @Test
    void naoDeveAceitarDescricaoVazia() {
        assertThrows(IllegalArgumentException.class, () ->
                Cardapio.criar(
                        "Nome",
                        "",
                        Collections.emptyList()
                )
        );
    }

    @Test
    void naoDeveAceitarNomeNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                Cardapio.criar(
                        null,
                        "Descricao",
                        Collections.emptyList()
                )
        );
    }
}
