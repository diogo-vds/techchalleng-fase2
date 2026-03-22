package com.postech.techchallenge.fase2.restaurante.infra.gateway;

import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;
import com.postech.techchallenge.fase2.restaurante.infra.persistence.entity.RestauranteEntity;
import com.postech.techchallenge.fase2.restaurante.infra.persistence.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RestauranteGatewayImpl implements RestauranteGateway {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        RestauranteEntity entity = new RestauranteEntity();
        entity.setNome(restaurante.getNome());
        entity.setEndereco(restaurante.getEndereco());
        entity.setTipoCozinha(restaurante.getTipoCozinha());
        entity.setHorarioFuncionamento(restaurante.getHorarioFuncionamento());
        entity.setDonoId(restaurante.getDonoId());
        restauranteRepository.save(entity);
        return restaurante;
    }

    @Override
    public Optional<Restaurante> buscarPorId(Long id) {
        return Optional.of(toDomain(restauranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante com ID " + id + " não encontrado"))));
    }

    @Override
    public List<Restaurante> listarTodos() {
        return restauranteRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deletar(Long id) {
        restauranteRepository.deleteById(id);
    }

    public Restaurante toDomain(RestauranteEntity entity){
        return new Restaurante(
                entity.getId(),
                entity.getNome(),
                entity.getEndereco(),
                entity.getTipoCozinha(),
                entity.getHorarioFuncionamento(),
                entity.getDonoId()
        );
    }
}