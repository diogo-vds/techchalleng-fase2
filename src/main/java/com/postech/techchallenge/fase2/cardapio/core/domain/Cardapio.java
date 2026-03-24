package com.postech.techchallenge.fase2.cardapio.core.domain;

import java.util.List;

public class Cardapio {

    private final Long id;
    private final String nome;
    private final List<ItemCardapio> itens;

    private Cardapio(Long id, String nome, List<ItemCardapio> itens) {
        validarCampo(nome, "nome");

        this.id = id;
        this.nome = nome.trim();
        this.itens = itens;
    }

    public static Cardapio criar(String nome, List<ItemCardapio> itens) {
        return new Cardapio(null, nome, itens);
    }

    public static Cardapio reconstruir(Long id, String nome, List<ItemCardapio> itens) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo na reconstrução");
        }
        return new Cardapio(id, nome, itens);
    }

    private static void validarCampo(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O campo " + campo + " é obrigatório");
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<ItemCardapio> getItens(){
        return itens;
    }
}
