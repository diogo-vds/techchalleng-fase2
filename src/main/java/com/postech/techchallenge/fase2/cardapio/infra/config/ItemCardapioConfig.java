package com.postech.techchallenge.fase2.cardapio.infra.config;

import com.postech.techchallenge.fase2.cardapio.core.gateway.ItemCardapioGateway;
import com.postech.techchallenge.fase2.cardapio.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemCardapioConfig {

    @Bean
    public CriarItemCardapioUseCase criarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway) {
        return new CriarItemCardapioUseCase(itemCardapioGateway);
    }

    @Bean
    public AtualizarItemCardapioUseCase atualizarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway) {
        return new AtualizarItemCardapioUseCase(itemCardapioGateway);
    }

    @Bean
    public DeletarItemCardapioUseCase deletarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway) {
        return new DeletarItemCardapioUseCase(itemCardapioGateway);
    }

    @Bean
    public BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase(ItemCardapioGateway itemCardapioGateway) {
        return new BuscarItemCardapioPorIdUseCase(itemCardapioGateway);
    }

    @Bean
    public ListarItemCardapioUseCase listarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway) {
        return new ListarItemCardapioUseCase(itemCardapioGateway);
    }
}
