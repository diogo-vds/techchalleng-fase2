package com.postech.techchallenge.fase2.endereco.infra.persistence.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoEntityTest {

    @Test
    void deveCriarEnderecoEntityComTodosCampos() {

        EnderecoEntity entity = new EnderecoEntity();

        entity.setId(1L);
        entity.setRua("Rua das Flores");
        entity.setNumero("123");
        entity.setComplemento("Apto 10");
        entity.setBairro("Centro");
        entity.setCidade("Curitiba");
        entity.setUf("PR");
        entity.setCep("80000000");

        assertEquals(1L, entity.getId());
        assertEquals("Rua das Flores", entity.getRua());
        assertEquals("123", entity.getNumero());
        assertEquals("Apto 10", entity.getComplemento());
        assertEquals("Centro", entity.getBairro());
        assertEquals("Curitiba", entity.getCidade());
        assertEquals("PR", entity.getUf());
        assertEquals("80000000", entity.getCep());
    }

    @Test
    void devePermitirComplementoNulo() {

        EnderecoEntity entity = new EnderecoEntity();

        entity.setRua("Rua A");
        entity.setNumero("100");
        entity.setComplemento(null);
        entity.setBairro("Batel");
        entity.setCidade("Curitiba");
        entity.setUf("PR");
        entity.setCep("80000000");

        assertNull(entity.getComplemento());
        assertEquals("Rua A", entity.getRua());
        assertEquals("100", entity.getNumero());
    }
}