package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;

public class DeletarCardapioUseCase {

    private final CardapioGateway cardapioGateway;

    public DeletarCardapioUseCase(CardapioGateway cardapioGateway) {
        this.cardapioGateway = cardapioGateway;
    }

    public void executar(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }

        boolean existe = cardapioGateway.buscarPorId(id).isPresent();

        if (!existe) {
            throw new IllegalArgumentException("Cardapio não encontrado");
        }

        cardapioGateway.deletar(id);
    }
}
