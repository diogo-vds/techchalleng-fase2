package com.postech.techchallenge.fase2.restaurante.core.usecase;

import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;

import java.util.UUID;

public class AtualizarRestauranteUseCase {

    private final RestauranteGateway gateway;

    public AtualizarRestauranteUseCase(RestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public Restaurante executar(UUID id,
                                String nome,
                                String endereco,
                                String tipoCozinha,
                                String horarioFuncionamento) {

        Restaurante restaurante = gateway.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não  encontrado"));

        restaurante.alterarDados(
                nome,
                endereco,
                tipoCozinha,
                horarioFuncionamento
        );

        return gateway.salvar(restaurante);
    }
}