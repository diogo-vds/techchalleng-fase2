package com.postech.techchallenge.fase2.endereco.core.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    void deveCriarEnderecoValido() {

        Endereco endereco = Endereco.criar(
                "Rua A",
                "123",
                "Apto 10",
                "Centro",
                "Curitiba",
                "PR",
                "80000000"
        );

        assertNull(endereco.getId());
        assertEquals("Rua A", endereco.getRua());
        assertEquals("123", endereco.getNumero());
        assertEquals("Apto 10", endereco.getComplemento());
        assertEquals("Centro", endereco.getBairro());
        assertEquals("Curitiba", endereco.getCidade());
        assertEquals("PR", endereco.getUf());
        assertEquals("80000000", endereco.getCep());
    }

    @Test
    void deveReconstruirEnderecoComId() {

        Endereco endereco = Endereco.reconstruir(
                1L,
                "Rua B",
                "456",
                "Casa",
                "Batel",
                "Curitiba",
                "PR",
                "80000000"
        );

        assertEquals(1L, endereco.getId());
        assertEquals("Rua B", endereco.getRua());
    }

    @Test
    void naoDeveReconstruirSemId() {

        assertThrows(IllegalArgumentException.class, () ->
                Endereco.reconstruir(
                        null,
                        "Rua B",
                        "456",
                        "Casa",
                        "Batel",
                        "Curitiba",
                        "PR",
                        "80000000"
                )
        );
    }

    @Test
    void deveRemoverMascaraDoCep() {

        Endereco endereco = Endereco.criar(
                "Rua A",
                "123",
                null,
                "Centro",
                "Curitiba",
                "PR",
                "80000-000"
        );

        assertEquals("80000000", endereco.getCep());
    }

    @Test
    void naoDeveAceitarCepInvalido() {

        assertThrows(IllegalArgumentException.class, () ->
                Endereco.criar(
                        "Rua A",
                        "123",
                        null,
                        "Centro",
                        "Curitiba",
                        "PR",
                        "123"
                )
        );
    }

    @Test
    void naoDeveAceitarUfInvalida() {

        assertThrows(IllegalArgumentException.class, () ->
                Endereco.criar(
                        "Rua A",
                        "123",
                        null,
                        "Centro",
                        "Curitiba",
                        "PARANA",
                        "80000000"
                )
        );
    }

    @Test
    void naoDeveAceitarCampoObrigatorioVazio() {

        assertThrows(IllegalArgumentException.class, () ->
                Endereco.criar(
                        "",
                        "123",
                        null,
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80000000"
                )
        );
    }

    @Test
    void complementoPodeSerNulo() {

        Endereco endereco = Endereco.criar(
                "Rua A",
                "123",
                null,
                "Centro",
                "Curitiba",
                "PR",
                "80000000"
        );

        assertNull(endereco.getComplemento());
    }

    @Test
    void equalsDeveRetornarTrueParaMesmoId() {

        Endereco e1 = Endereco.reconstruir(
                1L,"Rua","1",null,"Centro","Curitiba","PR","80000000");

        Endereco e2 = Endereco.reconstruir(
                1L,"Rua","1",null,"Centro","Curitiba","PR","80000000");

        assertEquals(e1, e2);
    }

    @Test
    void equalsDeveRetornarFalseParaIdsDiferentes() {

        Endereco e1 = Endereco.reconstruir(
                1L,"Rua","1",null,"Centro","Curitiba","PR","80000000");

        Endereco e2 = Endereco.reconstruir(
                2L,"Rua","1",null,"Centro","Curitiba","PR","80000000");

        assertNotEquals(e1, e2);
    }

    @Test
    void hashCodeNaoDeveSerNulo() {

        Endereco endereco = Endereco.reconstruir(
                1L,"Rua","1",null,"Centro","Curitiba","PR","80000000");

        assertNotNull(endereco.hashCode());
    }

    @Test
    void equalsDeveRetornarTrueParaMesmoObjeto() {

        Endereco endereco = Endereco.reconstruir(
                1L,"Rua","1",null,"Centro","Curitiba","PR","80000000");

        assertEquals(endereco, endereco);
    }

    @Test
    void equalsDeveRetornarFalseParaObjetoDeOutroTipo() {

        Endereco endereco = Endereco.reconstruir(
                1L,"Rua","1",null,"Centro","Curitiba","PR","80000000");

        assertNotEquals(endereco, "qualquer coisa");
    }

    @Test
    void naoDeveAceitarCampoNulo() {

        assertThrows(IllegalArgumentException.class, () ->
                Endereco.criar(
                        null,
                        "123",
                        null,
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80000000"
                )
        );
    }
}