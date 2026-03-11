package com.postech.techchallenge.fase2.restaurante.infra.db;

import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RestauranteGatewayImpl implements RestauranteGateway {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    @Override
    public Optional<Restaurante> buscarPorId(UUID id) {
        return restauranteRepository.findById(id);
    }

    @Override
    public List<Restaurante> listarTodos() {
        return restauranteRepository.findAll();
    }

    @Override
    public void deletar(UUID id) {
        restauranteRepository.deleteById(id);
    }
}