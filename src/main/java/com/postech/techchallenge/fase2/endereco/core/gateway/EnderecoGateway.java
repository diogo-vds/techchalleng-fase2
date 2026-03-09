package com.postech.techchallenge.fase2.endereco.core.gateway;

import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;

import java.util.List;
import java.util.Optional;

public interface EnderecoGateway {

    Endereco salvar(Endereco endereco);

    Optional<Endereco> buscarPorId(Long id);

    List<Endereco> listarTodos();

    void deletar(Long id);
}
