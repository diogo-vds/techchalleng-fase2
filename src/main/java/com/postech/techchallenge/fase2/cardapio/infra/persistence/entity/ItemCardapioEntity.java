package com.postech.techchallenge.fase2.cardapio.infra.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "item_cardapio")
public class ItemCardapioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cardapio_id", nullable = false)
    private CardapioEntity cardapio;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Boolean disponivelApenasRestaurante;

    @Column(nullable = false)
    private String caminhoFoto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CardapioEntity getCardapio() {
        return cardapio;
    }

    public void setCardapio(CardapioEntity cardapio) {
        this.cardapio = cardapio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Boolean getDisponivelApenasRestaurante() {
        return disponivelApenasRestaurante;
    }

    public void setDisponivelApenasRestaurante(Boolean disponivelApenasRestaurante) {
        this.disponivelApenasRestaurante = disponivelApenasRestaurante;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
}
