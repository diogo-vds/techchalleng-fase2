package com.postech.techchallenge.fase2.restaurante.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;


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
                                Long donoId) {

        validarDadosObrigatorios(nome, endereco, tipoCozinha, horarioFuncionamento, donoId);
        validarRestauranteUnico(nome, endereco);

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
                                          Long donoId) {
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
        if (donoId.compareTo(2L) != 0) {
            throw new IllegalArgumentException("Somente o dono pode criar um restaurante");
        }
    }

    private void validarRestauranteUnico(String nome, Endereco endereco) {
        if (gateway.existePorNomeEEndereco(nome, endereco)) {
            throw new IllegalArgumentException("Já existe um restaurante com este nome e endereço cadastrado.");
        }
    }


}