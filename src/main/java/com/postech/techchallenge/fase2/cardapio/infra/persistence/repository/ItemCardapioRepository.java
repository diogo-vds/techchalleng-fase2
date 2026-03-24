package com.postech.techchallenge.fase2.cardapio.infra.persistence.repository;

import com.postech.techchallenge.fase2.cardapio.infra.persistence.entity.ItemCardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCardapioRepository extends JpaRepository<ItemCardapioEntity, Long> {

    List<ItemCardapioEntity> findByCardapioId(Long cardapioId);
}
