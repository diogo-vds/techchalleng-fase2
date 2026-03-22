package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioInput;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioOutput;
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
                input.descricao(),
                input.preco(),
                input.disponivelApenasRestaurante(),
                input.caminhoFoto()
        );

        Cardapio salvo = cardapioGateway.salvar(cardapioAtualizado);

        return new CardapioOutput(
                salvo.getId(),
                salvo.getNome(),
                salvo.getDescricao(),
                salvo.getPreco(),
                salvo.getDisponivelApenasRestaurante(),
                salvo.getCaminhoFoto()
        );
    }
}
