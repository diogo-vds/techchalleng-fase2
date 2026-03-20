package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;

public class BuscarCardapioPorIdUseCase {

    private final CardapioGateway cardapioGateway;

    public BuscarCardapioPorIdUseCase(CardapioGateway cardapioGateway) {
        this.cardapioGateway = cardapioGateway;
    }

    public CardapioOutput executar(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }

        Cardapio cardapio = cardapioGateway
                .buscarPorId(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Cardapio não encontrado"));

        return new CardapioOutput(
                cardapio.getId(),
                cardapio.getNome(),
                cardapio.getDescricao(),
                cardapio.getPreco(),
                cardapio.getDisponivelApenasRestaurante(),
                cardapio.getCaminhoFoto()
        );
    }
}
