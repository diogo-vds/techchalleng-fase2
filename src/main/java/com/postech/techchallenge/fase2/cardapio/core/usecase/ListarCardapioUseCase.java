package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;

import java.util.List;
import java.util.stream.Collectors;

public class ListarCardapioUseCase {

    private final CardapioGateway cardapioGateway;

    public ListarCardapioUseCase(CardapioGateway cardapioGateway) {
        this.cardapioGateway = cardapioGateway;
    }

    public List<CardapioOutput> executar() {

        List<Cardapio> cardapios = cardapioGateway.listarTodos();

        return cardapios.stream()
                .map(cardapio -> new CardapioOutput(
                        cardapio.getId(),
                        cardapio.getNome(),
                        cardapio.getDescricao(),
                        cardapio.getItens()
                ))
                .collect(Collectors.toList());
    }
}
