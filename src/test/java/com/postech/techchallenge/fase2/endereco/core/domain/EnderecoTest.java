package com.postech.techchallenge.fase2.endereco.core.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    void deveCriarEnderecoValido() {

        Endereco endereco = Endereco.criar(
                "Rua das Flores",
                "123",
                "Apto 10",
                "Centro",
                "Curitiba",
                "PR",
                "80000000"
        );

        assertNull(endereco.getId());
        assertEquals("Rua das Flores", endereco.getRua());
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
                "Rua das Flores",
                "123",
                null,
                "Centro",
                "Curitiba",
                "PR",
                "80000000"
        );

        assertEquals(1L, endereco.getId());
        assertNull(endereco.getComplemento());
    }

    @Test
    void naoDeveReconstruirSemId() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Endereco.reconstruir(
                        null,
                        "Rua",
                        "123",
                        null,
                        "Centro",
                        "Curitiba",
                        "PR",
                        "80000000"
                )
        );

        assertEquals("Id não pode ser nulo na reconstrução", exception.getMessage());
    }

    @Test
    void deveRemoverCaracteresDoCep() {

        Endereco endereco = Endereco.criar(
                "Rua",
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

        assertThrows(
                IllegalArgumentException.class,
                () -> Endereco.criar(
                        "Rua",
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

        assertThrows(
                IllegalArgumentException.class,
                () -> Endereco.criar(
                        "Rua",
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
    void naoDeveAceitarCamposObrigatoriosVazios() {

        assertThrows(
                IllegalArgumentException.class,
                () -> Endereco.criar(
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
    void equalsDeveCompararPorId() {

        Endereco e1 = Endereco.reconstruir(
                1L, "Rua", "123", null, "Centro", "Curitiba", "PR", "80000000"
        );

        Endereco e2 = Endereco.reconstruir(
                1L, "Outra Rua", "999", null, "Bairro", "Cidade", "PR", "80000000"
        );

        assertEquals(e1, e2);
    }

    @Test
    void equalsDeveRetornarFalseParaIdsDiferentes() {

        Endereco e1 = Endereco.reconstruir(
                1L, "Rua", "123", null, "Centro", "Curitiba", "PR", "80000000"
        );

        Endereco e2 = Endereco.reconstruir(
                2L, "Rua", "123", null, "Centro", "Curitiba", "PR", "80000000"
        );

        assertNotEquals(e1, e2);
    }

}