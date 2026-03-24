package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.core.domain.ItemCardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioInput;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioInput;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;

public class AtualizarCardapioUseCase {

    private final CardapioGateway cardapioGateway;

    public AtualizarCardapioUseCase(CardapioGateway cardapioGateway) {
        this.cardapioGateway = cardapioGateway;
    }

    public CardapioOutput executar(CardapioInput input) {

        if (input == null) {
            throw new IllegalArgumentException("Input não pode ser nulo");
        }

        if (input.id() == null) {
            throw new IllegalArgumentException("Id é obrigatório para atualização");
        }

        Cardapio cardapioExistente = cardapioGateway
                .buscarPorId(input.id())
                .orElseThrow(() ->
                        new IllegalArgumentException("Cardapio não encontrado"));

        Cardapio cardapioAtualizado = Cardapio.reconstruir(
                cardapioExistente.getId(),
                input.nome(),
                input.itens().stream().map(
                        item -> ItemCardapio.reconstruir(
                                item.id(),
                                item.cardapioId(),
                                item.nome(),
                                item.descricao(),
                                item.preco(),
                                item.disponivelApenasRestaurante(),
                                item.caminhoFoto()
                        )
                ).toList()
        );

        Cardapio salvo = cardapioGateway.salvar(cardapioAtualizado);

        return new CardapioOutput(
                salvo.getId(),
                salvo.getNome(),
                salvo.getItens().stream().map(
                        item -> new ItemCardapioOutput(
                                item.getId(),
                                item.getCardapioId(),
                                item.getNome(),
                                item.getDescricao(),
                                item.getPreco(),
                                item.getDisponivelApenasRestaurante(),
                                item.getCaminhoFoto()
                        )).toList()
        );
    }
}
