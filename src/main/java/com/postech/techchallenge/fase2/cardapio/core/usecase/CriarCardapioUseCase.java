package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioInput;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;

public class CriarCardapioUseCase {

    private final CardapioGateway cardapioGateway;

    public CriarCardapioUseCase(CardapioGateway cardapioGateway) {
        this.cardapioGateway = cardapioGateway;
    }

    public CardapioOutput executar(CardapioInput input) {

        if (input == null) {
            throw new IllegalArgumentException("Input não pode ser nulo");
        }

        if (input.id() != null) {
            throw new IllegalArgumentException("Id não deve ser informado para criação");
        }

        Cardapio cardapio = Cardapio.criar(
                input.nome(),
                input.descricao(),
                input.itens()
        );

        Cardapio salvo = cardapioGateway.salvar(cardapio);

        return new CardapioOutput(
                salvo.getId(),
                salvo.getNome(),
                salvo.getDescricao(),
                salvo.getItens()
        );
    }
}
