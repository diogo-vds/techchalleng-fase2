package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.ItemCardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.ItemCardapioGateway;

import java.util.List;
import java.util.stream.Collectors;

public class ListarItemCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;

    public ListarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public List<ItemCardapioOutput> executar() {

        List<ItemCardapio> itens = itemCardapioGateway.listarTodos();

        return itens.stream()
                .map(cardapio -> new ItemCardapioOutput(
                        cardapio.getId(),
                        cardapio.getCardapioId(),
                        cardapio.getNome(),
                        cardapio.getDescricao(),
                        cardapio.getPreco(),
                        cardapio.getDisponivelApenasRestaurante(),
                        cardapio.getCaminhoFoto()
                ))
                .collect(Collectors.toList());
    }
}
