package com.postech.techchallenge.fase2.restaurante.infra.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;

import java.util.UUID;

public class RestauranteResponseDTO {

    @JsonProperty("id")
    private UUID id;

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


    public RestauranteResponseDTO() {}

    public RestauranteResponseDTO(Restaurante restaurante) {
        this.id = restaurante.getId();
        this.nome = restaurante.getNome();
        this.endereco = restaurante.getEndereco();
        this.tipoCozinha = restaurante.getTipoCozinha();
        this.horarioFuncionamento = restaurante.getHorarioFuncionamento();
        this.donoId = restaurante.getDonoId();
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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