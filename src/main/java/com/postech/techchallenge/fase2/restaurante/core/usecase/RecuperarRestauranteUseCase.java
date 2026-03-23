package com.postech.techchallenge.fase2.restaurante.core.usecase;

import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;
import com.postech.techchallenge.fase2.restaurante.infra.controller.dto.RestauranteResponseDTO;

import java.util.List;

public class RecuperarRestauranteUseCase {

    private final RestauranteGateway gateway;

    public RecuperarRestauranteUseCase(RestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public Restaurante porId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do restaurante é obrigatório");
        }

        return gateway.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado com ID: " + id));
    }

    public List<Restaurante> todos() {
        return gateway.listarTodos();
    }
}