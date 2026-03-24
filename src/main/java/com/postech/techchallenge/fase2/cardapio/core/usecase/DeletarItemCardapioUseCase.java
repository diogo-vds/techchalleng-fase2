package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.gateway.ItemCardapioGateway;

public class DeletarItemCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;

    public DeletarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public void executar(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }

        boolean existe = itemCardapioGateway.buscarPorId(id).isPresent();

        if (!existe) {
            throw new IllegalArgumentException("Item não encontrado");
        }

        itemCardapioGateway.deletar(id);
    }
}
