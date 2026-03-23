package com.postech.techchallenge.fase2.restaurante.infra.persistence.repository;

import com.postech.techchallenge.fase2.restaurante.infra.persistence.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {

    boolean existsByNomeAndEnderecoRuaAndEnderecoNumeroAndEnderecoCidadeAndEnderecoUf(
            String nome, String rua, String numero, String cidade, String uf);
}