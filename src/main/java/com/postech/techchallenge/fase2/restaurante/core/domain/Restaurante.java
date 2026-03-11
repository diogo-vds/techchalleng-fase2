package com.postech.techchallenge.fase2.restaurante.core.domain;

import java.util.UUID;

public class Restaurante {

    private UUID id;
    private String nome;
    private String endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;
    private UUID donoId;

    public Restaurante(UUID id,
                       String nome,
                       String endereco,
                       String tipoCozinha,
                       String horarioFuncionamento,
                       UUID donoId) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.donoId = donoId;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
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
                             String endereco,
                             String tipoCozinha,
                             String horarioFuncionamento) {
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
    }
}