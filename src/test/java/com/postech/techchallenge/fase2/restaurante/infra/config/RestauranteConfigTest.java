package com.postech.techchallenge.fase2.restaurante.infra.config;

import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;
import com.postech.techchallenge.fase2.restaurante.core.usecase.AtualizarRestauranteUseCase;
import com.postech.techchallenge.fase2.restaurante.core.usecase.CriarRestauranteUseCase;
import com.postech.techchallenge.fase2.restaurante.core.usecase.DeletarRestauranteUseCase;
import com.postech.techchallenge.fase2.restaurante.core.usecase.RecuperarRestauranteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

class gitRestauranteConfigTest {

    private RestauranteConfig config;
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void setup() {
        config = new RestauranteConfig();
        restauranteGateway = mock(RestauranteGateway.class);
    }

    @Test
    void deveCriarBeanCriarRestauranteUseCase() throws Exception {
        CriarRestauranteUseCase useCase = config.criarRestauranteUseCase(restauranteGateway);

        assertNotNull(useCase);
        assertSame(restauranteGateway, obterGateway(useCase));
    }

    @Test
    void deveCriarBeanRecuperarRestauranteUseCase() throws Exception {
        RecuperarRestauranteUseCase useCase = config.recuperarRestauranteUseCase(restauranteGateway);

        assertNotNull(useCase);
        assertSame(restauranteGateway, obterGateway(useCase));
    }

    @Test
    void deveCriarBeanAtualizarRestauranteUseCase() throws Exception {
        AtualizarRestauranteUseCase useCase = config.atualizarRestauranteUseCase(restauranteGateway);

        assertNotNull(useCase);
        assertSame(restauranteGateway, obterGateway(useCase));
    }

    @Test
    void deveCriarBeanDeletarRestauranteUseCase() throws Exception {
        DeletarRestauranteUseCase useCase = config.deletarRestauranteUseCase(restauranteGateway);

        assertNotNull(useCase);
        assertSame(restauranteGateway, obterGateway(useCase));
    }

    private Object obterGateway(Object useCase) throws Exception {
        Field field = useCase.getClass().getDeclaredField("gateway");
        field.setAccessible(true);
        return field.get(useCase);
    }
}
