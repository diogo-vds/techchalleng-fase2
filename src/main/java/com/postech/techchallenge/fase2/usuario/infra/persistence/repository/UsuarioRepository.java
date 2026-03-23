package com.postech.techchallenge.fase2.usuario.infra.persistence.repository;

import com.postech.techchallenge.fase2.usuario.infra.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmailIgnoreCase(String email);

    Optional<UsuarioEntity> findByTelefone(String telefone);

    Optional<UsuarioEntity> findByCpf(String cpf);
}
