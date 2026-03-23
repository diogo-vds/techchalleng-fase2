package com.postech.techchallenge.fase2.restaurante.core.gateway;

import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;

import java.util.List;
import java.util.Optional;

public interface RestauranteGateway {

    Restaurante salvar(Restaurante restaurante);

    Optional<Restaurante> buscarPorId(Long id);

    List<Restaurante> listarTodos();

    void deletar(Long id);

    boolean existePorNomeEEndereco(String nome, Endereco endereco);
}