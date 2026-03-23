package com.postech.techchallenge.fase2.usuario.infra.persistence.repository;

import com.postech.techchallenge.fase2.usuario.infra.persistence.entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Long> {

    java.util.Optional<TipoUsuarioEntity> findByNomeIgnoreCase(String nome);
}
