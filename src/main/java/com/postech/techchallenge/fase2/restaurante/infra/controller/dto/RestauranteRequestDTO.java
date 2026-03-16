package com.postech.techchallenge.fase2.restaurante.infra.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class RestauranteRequestDTO {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("endereco")
    private String endereco;

    @JsonProperty("tipo_cozinha")
    private String tipoCozinha;

    @JsonProperty("horario_funcionamento")
    private String horarioFuncionamento;

    @JsonProperty("dono_id")
    private UUID donoId;


    public RestauranteRequestDTO() {}


    public RestauranteRequestDTO(String nome, String endereco, String tipoCozinha,
                                 String horarioFuncionamento, UUID donoId) {
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.donoId = donoId;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public UUID getDonoId() {
        return donoId;
    }

    public void setDonoId(UUID donoId) {
        this.donoId = donoId;
    }
}