package com.postech.techchallenge.fase2.restaurante.infra.persistence.entity;

import com.postech.techchallenge.fase2.cardapio.infra.persistence.entity.CardapioEntity;
import com.postech.techchallenge.fase2.endereco.infra.persistence.entity.EnderecoEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "restaurante")
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @OneToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    private EnderecoEntity endereco;
    @Column(nullable = false)
    private String tipoCozinha;
    @ManyToOne
    @JoinColumn(name = "cardapio_id", nullable = false)
    private CardapioEntity cardapio;
    @Column(nullable = false)
    private String horarioFuncionamento;
    @Column(nullable = false)
    private Long donoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoEntity endereco) {
        this.endereco = endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public CardapioEntity getCardapio() {
        return cardapio;
    }

    public void setCardapio(CardapioEntity cardapio) {
        this.cardapio = cardapio;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public Long getDonoId() {
        return donoId;
    }

    public void setDonoId(Long donoId) {
        this.donoId = donoId;
    }
}
