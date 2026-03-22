package com.postech.techchallenge.fase2.cardapio.infra.config;

import com.postech.techchallenge.fase2.cardapio.core.usecase.*;
import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardapioConfig {

    @Bean
    public CriarCardapioUseCase criarCardapioUseCase(CardapioGateway cardapioGateway) {
        return new CriarCardapioUseCase(cardapioGateway);
    }

    @Bean
    public AtualizarCardapioUseCase atualizarCardapioUseCase(CardapioGateway cardapioGateway) {
        return new AtualizarCardapioUseCase(cardapioGateway);
    }

    @Bean
    public DeletarCardapioUseCase deletarCardapioUseCase(CardapioGateway cardapioGateway) {
        return new DeletarCardapioUseCase(cardapioGateway);
    }

    @Bean
    public BuscarCardapioPorIdUseCase buscarCardapioPorIdUseCase(CardapioGateway cardapioGateway) {
        return new BuscarCardapioPorIdUseCase(cardapioGateway);
    }

    @Bean
    public ListarCardapioUseCase listarCardapioUseCase(CardapioGateway cardapioGateway) {
        return new ListarCardapioUseCase(cardapioGateway);
    }
}
