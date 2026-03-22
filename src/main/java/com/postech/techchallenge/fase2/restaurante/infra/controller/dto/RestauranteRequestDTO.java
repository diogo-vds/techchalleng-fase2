package com.postech.techchallenge.fase2.restaurante.infra.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioInput;
import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoInput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class RestauranteRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @JsonProperty("nome")
    private String nome;

    @NotNull(message = "Endereço é obrigatório")
    @Valid
    @JsonProperty("endereco")
    private Endereco endereco;

    @NotBlank(message = "Tipo de cozinha é obrigatório")
    @JsonProperty("tipo_cozinha")
    private String tipoCozinha;

    @Valid
    @JsonProperty("cardapio")
    private Cardapio cardapio;

    @NotBlank(message = "Horário de funcionamento é obrigatório")
    @JsonProperty("horario_funcionamento")
    private String horarioFuncionamento;

    @NotNull(message = "ID do dono é obrigatório")
    @JsonProperty("dono_id")
    private UUID donoId;

    public RestauranteRequestDTO() {}

    public RestauranteRequestDTO(String nome,
                                 Endereco endereco,
                                 String tipoCozinha,
                                 Cardapio cardapio,
                                 String horarioFuncionamento,
                                 UUID donoId) {
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.cardapio = cardapio;
        this.horarioFuncionamento = horarioFuncionamento;
        this.donoId = donoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
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