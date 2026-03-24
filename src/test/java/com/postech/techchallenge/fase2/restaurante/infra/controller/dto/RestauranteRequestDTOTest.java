package com.postech.techchallenge.fase2.restaurante.infra.controller.dto;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteRequestDTOTest {

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
                Collections.emptyList()
        );
    }

    @Test
    void deveCriarComConstrutorVazioESetters() {

        RestauranteRequestDTO dto = new RestauranteRequestDTO();

        dto.setNome("Nome");
        dto.setEndereco(criarEndereco());
        dto.setTipoCozinha("Italiana");
        dto.setCardapio(criarCardapio());
        dto.setHorarioFuncionamento("08:00");
        dto.setDonoId(1L);

        assertEquals("Nome", dto.getNome());
        assertNotNull(dto.getEndereco());
        assertEquals("Italiana", dto.getTipoCozinha());
        assertNotNull(dto.getCardapio());
        assertEquals("08:00", dto.getHorarioFuncionamento());
        assertEquals(1L, dto.getDonoId());
    }

    @Test
    void deveCriarComConstrutorCompleto() {

        Endereco endereco = criarEndereco();
        Cardapio cardapio = criarCardapio();

        RestauranteRequestDTO dto = new RestauranteRequestDTO(
                "Nome",
                endereco,
                "Italiana",
                cardapio,
                "08:00",
                1L
        );

        assertEquals("Nome", dto.getNome());
        assertEquals(endereco, dto.getEndereco());
        assertEquals("Italiana", dto.getTipoCozinha());
        assertEquals(cardapio, dto.getCardapio());
        assertEquals("08:00", dto.getHorarioFuncionamento());
        assertEquals(1L, dto.getDonoId());
    }

    @Test
    void devePermitirValoresNulosOndeNaoTemValidacaoDireta() {

        RestauranteRequestDTO dto = new RestauranteRequestDTO();

        dto.setCardapio(null);

        assertNull(dto.getCardapio());
    }
}