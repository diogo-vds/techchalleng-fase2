package com.postech.techchallenge.fase2.restaurante.core.domain;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;

import java.util.UUID;

public class Restaurante {

    private Long id;
    private String nome;
    private Endereco endereco;
    private String tipoCozinha;
    private Cardapio cardapio;
    private String horarioFuncionamento;
    private UUID donoId;

    public Restaurante(Long id,
                       String nome,
                       Endereco endereco,
                       String tipoCozinha,
                       Cardapio cardapio,
                       String horarioFuncionamento,
                       UUID donoId) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cardapio = cardapio;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.donoId = donoId;
    }

    public Restaurante(String nome,
                       Endereco endereco,
                       String tipoCozinha,
                       Cardapio cardapio,
                       String horarioFuncionamento,
                       UUID donoId) {
        this.nome = nome;
        this.endereco = endereco;
        this.cardapio = cardapio;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.donoId = donoId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public UUID getDonoId() {
        return donoId;
    }

    public void alterarDados(String nome,
                             Endereco endereco,
                             String tipoCozinha,
                             Cardapio cardapio,
                             String horarioFuncionamento) {
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.cardapio = cardapio;
        this.horarioFuncionamento = horarioFuncionamento;
    }
}