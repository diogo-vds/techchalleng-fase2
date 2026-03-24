package com.postech.techchallenge.fase2.cardapio.core.dto;

import java.util.List;

public record CardapioInput(
        Long id,
        String nome,
        List<ItemCardapioInput> itens
) {
}
