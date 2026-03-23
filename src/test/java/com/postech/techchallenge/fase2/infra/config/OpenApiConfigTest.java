package com.postech.techchallenge.fase2.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OpenApiConfigTest {

    private final OpenApiConfig config = new OpenApiConfig();

    @Test
    void deveCriarCustomOpenApiComMetadadosEsperados() {
        OpenAPI openAPI = config.customOpenAPI();

        assertNotNull(openAPI);
        assertNotNull(openAPI.getInfo());
        assertEquals("Tech Challenge Fase 2 API", openAPI.getInfo().getTitle());
        assertEquals("API para gerenciamento de usuarios, enderecos, restaurantes e cardapio.",
                openAPI.getInfo().getDescription());
        assertEquals("v1", openAPI.getInfo().getVersion());
        assertNotNull(openAPI.getInfo().getContact());
        assertEquals("Grupo Tech Challenge", openAPI.getInfo().getContact().getName());
    }
}
