package com.postech.techchallenge.fase2.restaurante.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;

import java.util.UUID;

public class CriarRestauranteUseCase {

    private final RestauranteGateway gateway;

    public CriarRestauranteUseCase(RestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public Restaurante executar(String nome,
                                Endereco endereco,
                                String tipoCozinha,
                                Cardapio cardapio,
                                String horarioFuncionamento,
                                UUID donoId) {

        validarDadosObrigatorios(nome, endereco, tipoCozinha, horarioFuncionamento, donoId);

        Restaurante restaurante = new Restaurante(
                nome,
                endereco,
                tipoCozinha,
                cardapio,
                horarioFuncionamento,
                donoId
        );

        return gateway.salvar(restaurante);
    }

    private void validarDadosObrigatorios(String nome,
                                          Endereco endereco,
                                          String tipoCozinha,
                                          String horarioFuncionamento,
                                          UUID donoId) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do restaurante é obrigatório");
        }
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço do restaurante é obrigatório");
        }
        if (tipoCozinha == null || tipoCozinha.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de cozinha é obrigatório");
        }
        if (horarioFuncionamento == null || horarioFuncionamento.trim().isEmpty()) {
            throw new IllegalArgumentException("Horário de funcionamento é obrigatório");
        }
        if (donoId == null) {
            throw new IllegalArgumentException("ID do dono é obrigatório");
        }
    }
}