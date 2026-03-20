package com.postech.techchallenge.fase2.cardapio.core.domain;

import java.math.BigDecimal;

public class Cardapio {

    private final Long id;
    private final String nome;
    private final String descricao;
    private final BigDecimal preco;
    private final Boolean disponivelApenasRestaurante;
    private final String caminhoFoto;

    private Cardapio(Long id, String nome, String descricao, BigDecimal preco, Boolean disponivelApenasRestaurante, String caminhoFoto) {
        validarCampo(nome, "nome");
        validarCampo(descricao, "descrição");
        validarCampo(caminhoFoto, "caminho da foto");

        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço inválido");
        }

        this.id = id;
        this.nome = nome.trim();
        this.descricao = descricao.trim();
        this.preco = preco;
        this.disponivelApenasRestaurante = disponivelApenasRestaurante;
        this.caminhoFoto = caminhoFoto.trim();
    }

    public static Cardapio criar(String nome, String descricao, BigDecimal preco, Boolean disponivelApenasRestaurante, String caminhoFoto) {
        return new Cardapio(null, nome, descricao, preco, disponivelApenasRestaurante, caminhoFoto);
    }

    public static Cardapio reconstruir(Long id, String nome, String descricao, BigDecimal preco, Boolean disponivelApenasRestaurante, String caminhoFoto) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo na reconstrução");
        }
        return new Cardapio(id, nome, descricao, preco, disponivelApenasRestaurante, caminhoFoto);
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

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Boolean getDisponivelApenasRestaurante() {
        return disponivelApenasRestaurante;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }
}
