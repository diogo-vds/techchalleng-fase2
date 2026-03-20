package com.postech.techchallenge.fase2.cardapio.core.dto;

import java.math.BigDecimal;

public record CardapioInput(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponivelApenasRestaurante,
        String caminhoFoto
) {
}
