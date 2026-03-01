package com.postech.techchallenge.fase2.usuario.infra.gateway.gateway;

import com.postech.techchallenge.fase2.usuario.infra.gateway.domain.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(String email);

    Optional<UsuarioEntity> findByCpf(String cpf);

}
