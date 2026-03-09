package com.postech.techchallenge.fase2.endereco.infra.persistence.repository;

import com.postech.techchallenge.fase2.endereco.infra.persistence.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
}
