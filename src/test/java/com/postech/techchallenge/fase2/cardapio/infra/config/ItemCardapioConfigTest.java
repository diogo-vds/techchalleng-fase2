package com.postech.techchallenge.fase2.cardapio.infra.config;

import com.postech.techchallenge.fase2.cardapio.core.gateway.ItemCardapioGateway;
import com.postech.techchallenge.fase2.cardapio.core.usecase.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

class ItemCardapioConfigTest {

    private ItemCardapioConfig config;
    private ItemCardapioGateway itemCardapioGateway;

    @BeforeEach
    void setup() {
        config = new ItemCardapioConfig();
        itemCardapioGateway = mock(ItemCardapioGateway.class);
    }

    @Test
    void deveCriarBeanCriarItemCardapioUseCase() throws Exception {
        CriarItemCardapioUseCase useCase = config.criarItemCardapioUseCase(itemCardapioGateway);

        assertNotNull(useCase);
        assertSame(itemCardapioGateway, obterGateway(useCase));
    }

    @Test
    void deveCriarBeanAtualizarItemCardapioUseCase() throws Exception {
        AtualizarItemCardapioUseCase useCase = config.atualizarItemCardapioUseCase(itemCardapioGateway);

        assertNotNull(useCase);
        assertSame(itemCardapioGateway, obterGateway(useCase));
    }

    @Test
    void deveCriarBeanDeletarItemCardapioUseCase() throws Exception {
        DeletarItemCardapioUseCase useCase = config.deletarItemCardapioUseCase(itemCardapioGateway);

        assertNotNull(useCase);
        assertSame(itemCardapioGateway, obterGateway(useCase));
    }

    @Test
    void deveCriarBeanBuscarItemCardapioPorIdUseCase() throws Exception {
        BuscarItemCardapioPorIdUseCase useCase = config.buscarItemCardapioPorIdUseCase(itemCardapioGateway);

        assertNotNull(useCase);
        assertSame(itemCardapioGateway, obterGateway(useCase));
    }

    @Test
    void deveCriarBeanListarItemCardapioUseCase() throws Exception {
        ListarItemCardapioUseCase useCase = config.listarItemCardapioUseCase(itemCardapioGateway);

        assertNotNull(useCase);
        assertSame(itemCardapioGateway, obterGateway(useCase));
    }

    private Object obterGateway(Object useCase) throws Exception {
        Field field = useCase.getClass().getDeclaredField("itemCardapioGateway");
        field.setAccessible(true);
        return field.get(useCase);
    }
}
