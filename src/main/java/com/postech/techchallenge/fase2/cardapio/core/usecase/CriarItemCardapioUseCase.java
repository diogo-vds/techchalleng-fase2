package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.ItemCardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioInput;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.ItemCardapioGateway;

public class CriarItemCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;

    public CriarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public ItemCardapioOutput executar(ItemCardapioInput input) {

        if (input == null) {
            throw new IllegalArgumentException("Input não pode ser nulo");
        }

        if (input.id() != null) {
            throw new IllegalArgumentException("Id não deve ser informado para criação");
        }

        if (input.cardapioId() == null) {
            throw new IllegalArgumentException("Id do cardapio não deve ser informado para criação do item");
        }

        ItemCardapio item = ItemCardapio.criar(
                input.cardapioId(),
                input.nome(),
                input.descricao(),
                input.preco(),
                input.disponivelApenasRestaurante(),
                input.caminhoFoto()
        );

        ItemCardapio salvo = itemCardapioGateway.salvar(item);

        return new ItemCardapioOutput(
                salvo.getId(),
                salvo.getCardapioId(),
                salvo.getNome(),
                salvo.getDescricao(),
                salvo.getPreco(),
                salvo.getDisponivelApenasRestaurante(),
                salvo.getCaminhoFoto()
        );
    }
}
