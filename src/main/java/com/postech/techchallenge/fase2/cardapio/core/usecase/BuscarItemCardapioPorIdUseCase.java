package com.postech.techchallenge.fase2.cardapio.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.ItemCardapio;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.gateway.ItemCardapioGateway;

public class BuscarItemCardapioPorIdUseCase {

    private final ItemCardapioGateway itemCardapioGateway;

    public BuscarItemCardapioPorIdUseCase(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public ItemCardapioOutput executar(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }

        ItemCardapio itemCardapio = itemCardapioGateway
                .buscarPorId(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Item não encontrado"));

        return new ItemCardapioOutput(
                itemCardapio.getId(),
                itemCardapio.getCardapioId(),
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getDisponivelApenasRestaurante(),
                itemCardapio.getCaminhoFoto()
        );
    }
}
