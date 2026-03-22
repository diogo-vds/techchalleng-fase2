package com.postech.techchallenge.fase2.restaurante.infra.config;

import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;
import com.postech.techchallenge.fase2.restaurante.core.usecase.AtualizarRestauranteUseCase;
import com.postech.techchallenge.fase2.restaurante.core.usecase.CriarRestauranteUseCase;
import com.postech.techchallenge.fase2.restaurante.core.usecase.DeletarRestauranteUseCase;
import com.postech.techchallenge.fase2.restaurante.core.usecase.RecuperarRestauranteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestauranteConfig {

    @Bean
    public CriarRestauranteUseCase criarRestauranteUseCase(RestauranteGateway restauranteGateway) {
        return new CriarRestauranteUseCase(restauranteGateway);
    }

    @Bean
    public RecuperarRestauranteUseCase recuperarRestauranteUseCase(RestauranteGateway restauranteGateway) {
        return new RecuperarRestauranteUseCase(restauranteGateway);
    }

    @Bean
    public AtualizarRestauranteUseCase atualizarRestauranteUseCase(RestauranteGateway restauranteGateway) {
        return new AtualizarRestauranteUseCase(restauranteGateway);
    }

    @Bean
    public DeletarRestauranteUseCase deletarRestauranteUseCase(RestauranteGateway restauranteGateway) {
        return new DeletarRestauranteUseCase(restauranteGateway);
    }

}
