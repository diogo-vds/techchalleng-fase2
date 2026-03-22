package com.postech.techchallenge.fase2.restaurante.core.domain;


public class Restaurante {

    private Long id;
    private String nome;
    private String endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;
    private Long donoId;

    public Restaurante(Long id,
                       String nome,
                       String endereco,
                       String tipoCozinha,
                       String horarioFuncionamento,
                       Long donoId) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.donoId = donoId;
    }

    public Restaurante(String nome,
                       String endereco,
                       String tipoCozinha,
                       String horarioFuncionamento,
                       Long donoId) {
        this.nome = nome;
        this.endereco = endereco;
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

    public String getEndereco() {
        return endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public Long getDonoId() {
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