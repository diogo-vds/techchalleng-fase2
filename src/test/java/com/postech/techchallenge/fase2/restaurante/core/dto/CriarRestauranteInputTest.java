package com.postech.techchallenge.fase2.restaurante.core.dto;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class CriarRestauranteInputTest {

    private TipoUsuario criarTipoUsuario() {
        return new TipoUsuario(1L, "DONO");
    }

    @Test
    void deveCriarRecordECobrirGetters() {

        TipoUsuario tipoUsuario = criarTipoUsuario();

        CriarRestauranteInput input = new CriarRestauranteInput(
                "Restaurante X",
                "João",
                tipoUsuario,
                "Rua A, 123",
                "Italiana",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                "Pizza, Massa"
        );

        assertEquals("Restaurante X", input.nomeRestaurante());
        assertEquals("João", input.donoRestaurante());
        assertEquals(tipoUsuario, input.tipoUsuario());
        assertEquals("Rua A, 123", input.enderecoRestaurante());
        assertEquals("Italiana", input.tipoCozinha());
        assertEquals(LocalTime.of(8, 0), input.horarioAbertura());
        assertEquals(LocalTime.of(22, 0), input.horarioFechamento());
        assertEquals("Pizza, Massa", input.cardapio());
    }

    @Test
    void deveTestarEqualsEHashCode() {

        TipoUsuario tipoUsuario = criarTipoUsuario();

        CriarRestauranteInput input1 = new CriarRestauranteInput(
                "Restaurante X",
                "João",
                tipoUsuario,
                "Rua A",
                "Italiana",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                "Cardápio"
        );

        CriarRestauranteInput input2 = new CriarRestauranteInput(
                "Restaurante X",
                "João",
                tipoUsuario,
                "Rua A",
                "Italiana",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                "Cardápio"
        );

        assertEquals(input1, input2);
        assertEquals(input1.hashCode(), input2.hashCode());
    }

    @Test
    void deveTestarToString() {

        CriarRestauranteInput input = new CriarRestauranteInput(
                "Restaurante X",
                "João",
                criarTipoUsuario(),
                "Rua A",
                "Italiana",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                "Cardápio"
        );

        String resultado = input.toString();

        assertTrue(resultado.contains("Restaurante X"));
        assertTrue(resultado.contains("João"));
    }
}