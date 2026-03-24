package com.postech.techchallenge.fase2.restaurante.infra.controller.dto;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteResponseDTOTest {

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

    private Restaurante criarRestaurante() {
        return new Restaurante(
                1L,
                "Nome",
                criarEndereco(),
                "Italiana",
                criarCardapio(),
                "08:00",
                1L
        );
    }

    @Test
    void deveCriarComConstrutorVazioESetters() {

        RestauranteResponseDTO dto = new RestauranteResponseDTO();

        dto.setId(1L);
        dto.setNome("Nome");
        dto.setEndereco("Rua A, 123");
        dto.setTipoCozinha("Italiana");
        dto.setHorarioFuncionamento("08:00");
        dto.setDonoId(1L);

        assertEquals(1L, dto.getId());
        assertEquals("Nome", dto.getNome());
        assertEquals("Rua A, 123", dto.getEndereco());
        assertEquals("Italiana", dto.getTipoCozinha());
        assertEquals("08:00", dto.getHorarioFuncionamento());
        assertEquals(1L, dto.getDonoId());
    }

    @Test
    void deveCriarComConstrutorCompleto() {

        RestauranteResponseDTO dto = new RestauranteResponseDTO(
                1L,
                "Nome",
                "Rua A, 123",
                "Italiana",
                "08:00",
                1L
        );

        assertEquals(1L, dto.getId());
        assertEquals("Nome", dto.getNome());
        assertEquals("Rua A, 123", dto.getEndereco());
        assertEquals("Italiana", dto.getTipoCozinha());
        assertEquals("08:00", dto.getHorarioFuncionamento());
        assertEquals(1L, dto.getDonoId());
    }

    @Test
    void deveExecutarConstrutorComRestaurante() {

        Restaurante restaurante = criarRestaurante();

        RestauranteResponseDTO dto = new RestauranteResponseDTO(restaurante);

        assertNotNull(dto);
    }
}