package com.postech.techchallenge.fase2.cardapio.core.domain;

import java.math.BigDecimal;

public class ItemCardapio {

    private final Long id;
    private final Long cardapioId;
    private final String nome;
    private final String descricao;
    private final BigDecimal preco;
    private final Boolean disponivelApenasRestaurante;
    private final String caminhoFoto;

    private ItemCardapio(Long id, Long cardapioId, String nome, String descricao, BigDecimal preco, Boolean disponivelApenasRestaurante, String caminhoFoto) {
        validarCampo(nome, "nome");
        validarCampo(descricao, "descrição");
        validarCampo(caminhoFoto, "caminho da foto");
        if(cardapioId == null || cardapioId < 1){
            throw new IllegalArgumentException("Obrigatório informar o cardápio para cadastrar um item");
        };

        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço inválido");
        }

        this.id = id;
        this.cardapioId = cardapioId;
        this.nome = nome.trim();
        this.descricao = descricao.trim();
        this.preco = preco;
        this.disponivelApenasRestaurante = disponivelApenasRestaurante;
        this.caminhoFoto = caminhoFoto.trim();
    }

    public static ItemCardapio criar(Long cardapioId, String nome, String descricao, BigDecimal preco, Boolean disponivelApenasRestaurante, String caminhoFoto) {
        return new ItemCardapio(null, cardapioId, nome, descricao, preco, disponivelApenasRestaurante, caminhoFoto);
    }

    public static ItemCardapio reconstruir(Long id, Long cardapioId, String nome, String descricao, BigDecimal preco, Boolean disponivelApenasRestaurante, String caminhoFoto) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo na reconstrução");
        }
        return new ItemCardapio(id, cardapioId, nome, descricao, preco, disponivelApenasRestaurante, caminhoFoto);
    }

    private static void validarCampo(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O campo " + campo + " é obrigatório");
        }
    }

    public Long getId() {
        return id;
    }

    public Long getCardapioId() {
        return cardapioId;
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
