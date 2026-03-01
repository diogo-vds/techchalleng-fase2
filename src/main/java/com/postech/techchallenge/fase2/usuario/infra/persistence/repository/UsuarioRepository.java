package com.postech.techchallenge.fase2.usuario.infra.persistence.repository;

import com.postech.techchallenge.fase2.usuario.infra.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
