package com.postech.techchallenge.fase2.restaurante.infra.persistence.repository;

import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, UUID> {
}