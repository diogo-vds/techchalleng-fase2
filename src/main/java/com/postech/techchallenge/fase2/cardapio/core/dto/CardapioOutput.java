package com.postech.techchallenge.fase2.cardapio.core.dto;

import java.util.List;

public record CardapioOutput(
        Long id,
        String nome,
        List<ItemCardapioOutput> itens
) {
}
