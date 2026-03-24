package com.postech.techchallenge.fase2.cardapio.core.dto;

import java.math.BigDecimal;

public record ItemCardapioOutput(
        Long id,
        Long cardapioId,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponivelApenasRestaurante,
        String caminhoFoto
) {
}
