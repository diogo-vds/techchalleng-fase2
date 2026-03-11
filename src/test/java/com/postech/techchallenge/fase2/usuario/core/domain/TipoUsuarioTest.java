package com.postech.techchallenge.fase2.usuario.core.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TipoUsuarioTest {

    @Test
    void deveCriarTipoUsuarioComSucesso() {
        TipoUsuario tipo = new TipoUsuario(1L, "ADMIN");

        assertEquals(1L, tipo.getId());
        assertEquals("ADMIN", tipo.getDescricao());
    }

    @Test
    void naoDeveCriarTipoUsuarioComNomeNulo() {
        assertThrows(IllegalArgumentException.class, () -> new TipoUsuario(1L, null));
    }

    @Test
    void naoDeveCriarTipoUsuarioComNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> new TipoUsuario(1L, ""));
    }

    @Test
    void naoDeveCriarTipoUsuarioComNomeEmBranco() {
        assertThrows(IllegalArgumentException.class, () -> new TipoUsuario(1L, "   "));
    }
}
