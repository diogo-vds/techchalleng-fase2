package com.postech.techchallenge.fase2.restaurante.infra.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;

public class RestauranteResponseDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("endereco")
    private String endereco;

    @JsonProperty("tipo_cozinha")
    private String tipoCozinha;

    @JsonProperty("horario_funcionamento")
    private String horarioFuncionamento;

    @JsonProperty("dono_id")
    private Long donoId;


    public RestauranteResponseDTO() {}

    public RestauranteResponseDTO(Long id, String nome, String endereco, String tipoCozinha, String horarioFuncionamento, Long donoId) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.donoId = donoId;
    }

    public RestauranteResponseDTO(Restaurante restaurante) {
    }


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

    public Long getDonoId() {
        return donoId;
    }

    public void setDonoId(Long donoId) {
        this.donoId = donoId;
    }
}