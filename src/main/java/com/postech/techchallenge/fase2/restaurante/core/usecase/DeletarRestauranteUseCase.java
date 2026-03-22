package com.postech.techchallenge.fase2.restaurante.core.usecase;

import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;


public class DeletarRestauranteUseCase {

    private final RestauranteGateway gateway;

    public DeletarRestauranteUseCase(RestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public void executar(Long id) {

        gateway.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        gateway.deletar(id);
    }
}