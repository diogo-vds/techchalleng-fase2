package com.postech.techchallenge.fase2.usuario.core.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    void deveCriarUsuarioComSucesso() {

        TipoUsuario tipo = new TipoUsuario(1L, "ADMIN");

        Usuario usuario = Usuario.criar(
                "João",
                "joao@email.com",
                "41999999999",
                "12345678900",
                tipo
        );

        assertNull(usuario.getId());
        assertEquals("João", usuario.getNome());
        assertEquals("joao@email.com", usuario.getEmail());
        assertEquals("41999999999", usuario.getTelefone());
        assertEquals("12345678900", usuario.getCpf());
        assertEquals("ADMIN", usuario.getTipoUsuario().getDescricao());
    }

    @Test
    void deveReconstruirUsuarioComSucesso() {

        TipoUsuario tipo = new TipoUsuario(2L, "CLIENTE");

        Usuario usuario = Usuario.reconstruir(
                10L,
                "Maria",
                "maria@email.com",
                "41888888888",
                "98765432100",
                tipo
        );

        assertEquals(10L, usuario.getId());
        assertEquals("Maria", usuario.getNome());
        assertEquals("CLIENTE", usuario.getTipoUsuario().getDescricao());
    }

    @Test
    void naoDeveCriarUsuarioSemTipo() {

        assertThrows(IllegalArgumentException.class, () ->
                Usuario.criar(
                        "Carlos",
                        "carlos@email.com",
                        "41777777777",
                        "11122233344",
                        null
                )
        );
    }

    @Test
    void naoDeveReconstruirUsuarioSemId() {

        TipoUsuario tipo = new TipoUsuario(1L, "ADMIN");

        assertThrows(IllegalArgumentException.class, () ->
                Usuario.reconstruir(
                        null,
                        "Pedro",
                        "pedro@email.com",
                        "41666666666",
                        "99988877766",
                        tipo
                )
        );
    }
}