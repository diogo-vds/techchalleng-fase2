package com.postech.techchallenge.fase2.cardapio.core.gateway;

import com.postech.techchallenge.fase2.cardapio.core.domain.ItemCardapio;

import java.util.List;
import java.util.Optional;

public interface ItemCardapioGateway {

    ItemCardapio salvar(ItemCardapio cardapio);

    Optional<ItemCardapio> buscarPorId(Long id);

    List<ItemCardapio> listarTodos();

    void deletar(Long id);
}
