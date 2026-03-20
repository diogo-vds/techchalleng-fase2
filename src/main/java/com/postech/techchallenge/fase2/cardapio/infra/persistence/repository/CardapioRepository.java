package com.postech.techchallenge.fase2.cardapio.infra.persistence.repository;

import com.postech.techchallenge.fase2.cardapio.infra.persistence.entity.CardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardapioRepository extends JpaRepository<CardapioEntity, Long> {
}
