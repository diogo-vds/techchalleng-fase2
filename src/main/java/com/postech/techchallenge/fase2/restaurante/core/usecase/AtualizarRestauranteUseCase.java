package com.postech.techchallenge.fase2.restaurante.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;

public class AtualizarRestauranteUseCase {

    private final RestauranteGateway gateway;

    public AtualizarRestauranteUseCase(RestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public Restaurante executar(Long id,
                                String nome,
                                Endereco endereco,
                                String tipoCozinha,
                                Cardapio cardapio,
                                String horarioFuncionamento) {

        Restaurante restaurante = gateway.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        restaurante.alterarDados(nome, endereco, tipoCozinha, cardapio, horarioFuncionamento);

        return gateway.salvar(restaurante);
    }
}