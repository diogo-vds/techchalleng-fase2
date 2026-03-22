package com.postech.techchallenge.fase2.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tech Challenge Fase 2 API")
                        .description("API para gerenciamento de usuarios, enderecos, restaurantes e cardapio.")
                        .version("v1")
                        .contact(new Contact()
                                .name("Grupo Tech Challenge")));
    }
}
