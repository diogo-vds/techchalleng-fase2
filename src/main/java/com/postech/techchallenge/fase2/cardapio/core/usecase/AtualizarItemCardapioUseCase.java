package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.ItemCardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioInput;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.ItemCardapioGateway;

public class AtualizarItemCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;

    public AtualizarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public ItemCardapioOutput executar(ItemCardapioInput input) {

        if (input == null) {
            throw new IllegalArgumentException("Input não pode ser nulo");
        }

        if (input.id() == null) {
            throw new IllegalArgumentException("Id é obrigatório para atualização");
        }

        ItemCardapio itemCardapioExistente = itemCardapioGateway
                .buscarPorId(input.id())
                .orElseThrow(() ->
                        new IllegalArgumentException("Item não encontrado"));

        ItemCardapio itemCardapioAtualizado = ItemCardapio.reconstruir(
                itemCardapioExistente.getId(),
                input.cardapioId(),
                input.nome(),
                input.descricao(),
                input.preco(),
                input.disponivelApenasRestaurante(),
                input.caminhoFoto()
        );

        ItemCardapio salvo = itemCardapioGateway.salvar(itemCardapioAtualizado);

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
