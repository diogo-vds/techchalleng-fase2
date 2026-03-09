package com.postech.techchallenge.fase2.endereco.infra.config;

import com.postech.techchallenge.fase2.endereco.core.usecase.*;
import com.postech.techchallenge.fase2.endereco.core.gateway.EnderecoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnderecoConfig {

    @Bean
    public CriarEnderecoUseCase criarEnderecoUseCase(EnderecoGateway enderecoGateway) {
        return new CriarEnderecoUseCase(enderecoGateway);
    }

    @Bean
    public AtualizarEnderecoUseCase atualizarEnderecoUseCase(EnderecoGateway enderecoGateway) {
        return new AtualizarEnderecoUseCase(enderecoGateway);
    }

    @Bean
    public DeletarEnderecoUseCase deletarEnderecoUseCase(EnderecoGateway enderecoGateway) {
        return new DeletarEnderecoUseCase(enderecoGateway);
    }

    @Bean
    public BuscarEnderecoPorIdUseCase buscarEnderecoPorIdUseCase(EnderecoGateway enderecoGateway) {
        return new BuscarEnderecoPorIdUseCase(enderecoGateway);
    }

    @Bean
    public ListarEnderecosUseCase listarEnderecosUseCase(EnderecoGateway enderecoGateway) {
        return new ListarEnderecosUseCase(enderecoGateway);
    }
}