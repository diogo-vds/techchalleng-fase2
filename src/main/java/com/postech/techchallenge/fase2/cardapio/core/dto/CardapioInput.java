package com.postech.techchallenge.fase2.cardapio.core.dto;

import com.postech.techchallenge.fase2.cardapio.core.domain.ItemCardapio;

import java.util.List;

public record CardapioInput(
        Long id,
        String nome,
        String descricao,
        List<ItemCardapio> itens
) {
}
