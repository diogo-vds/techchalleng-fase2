package com.postech.techchallenge.fase2.restaurante.core.gateway;

import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestauranteGateway {

    Restaurante salvar(Restaurante restaurante);

    Optional<Restaurante> buscarPorId(UUID id);

    List<Restaurante> listarTodos();

    void deletar(UUID id);
}