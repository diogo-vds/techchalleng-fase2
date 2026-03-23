package com.postech.techchallenge.fase2.restaurante.core.domain;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {

    private Endereco criarEndereco() {
        return Endereco.criar(
                "Rua A", "123", null,
                "Centro", "São Paulo", "SP", "12345678"
        );
    }

    private Cardapio criarCardapio() {
        return Cardapio.criar(
                "Pizza",
                "Descrição",
                new BigDecimal("50.00"),
                true,
                "/img.png"
        );
    }

    @Test
    void deveCobrirConstrutorComId() {
        Restaurante r = new Restaurante(
                1L,
                "Nome",
                criarEndereco(),
                "Italiana",
                criarCardapio(),
                "08:00",
                10L
        );

        assertEquals(1L, r.getId());
    }

    @Test
    void deveCobrirConstrutorSemId() {
        Restaurante r = new Restaurante(
                "Nome",
                criarEndereco(),
                "Italiana",
                criarCardapio(),
                "08:00",
                10L
        );

        assertNull(r.getId());
    }

    @Test
    void deveCobrirTodosSettersEGetters() {
        Restaurante r = new Restaurante(
                "Nome",
                criarEndereco(),
                "Italiana",
                criarCardapio(),
                "08:00",
                10L
        );

        Endereco novoEndereco = criarEndereco();
        Cardapio novoCardapio = criarCardapio();

        r.setId(99L);
        r.setNome("Novo Nome");
        r.setEndereco(novoEndereco);
        r.setTipoCozinha("Japonesa");
        r.setCardapio(novoCardapio);
        r.setHorarioFuncionamento("10:00");
        r.setDonoId(500L);

        assertEquals(99L, r.getId());
        assertEquals("Novo Nome", r.getNome());
        assertEquals(novoEndereco, r.getEndereco());
        assertEquals("Japonesa", r.getTipoCozinha());
        assertEquals(novoCardapio, r.getCardapio());
        assertEquals("10:00", r.getHorarioFuncionamento());
        assertEquals(500L, r.getDonoId());
    }

    @Test
    void deveCobrirAlterarDados() {
        Restaurante r = new Restaurante(
                "Antigo",
                criarEndereco(),
                "Brasileira",
                criarCardapio(),
                "09:00",
                1L
        );

        Endereco novoEndereco = Endereco.criar(
                "Rua Nova", "999", null,
                "Centro", "Rio", "RJ", "87654321"
        );

        Cardapio novoCardapio = Cardapio.criar(
                "Sushi",
                "Descrição",
                new BigDecimal("100.00"),
                false,
                "/sushi.png"
        );

        r.alterarDados(
                "Novo Nome",
                novoEndereco,
                "Japonesa",
                novoCardapio,
                "10:00"
        );

        assertEquals("Novo Nome", r.getNome());
        assertEquals(novoEndereco, r.getEndereco());
        assertEquals("Japonesa", r.getTipoCozinha());
        assertEquals(novoCardapio, r.getCardapio());
        assertEquals("10:00", r.getHorarioFuncionamento());
    }
}