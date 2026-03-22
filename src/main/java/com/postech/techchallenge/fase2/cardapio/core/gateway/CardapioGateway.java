package com.postech.techchallenge.fase2.cardapio.core.gateway;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;

import java.util.List;
import java.util.Optional;

public interface CardapioGateway {

    Cardapio salvar(Cardapio cardapio);

    Optional<Cardapio> buscarPorId(Long id);

    List<Cardapio> listarTodos();

    void deletar(Long id);
}
