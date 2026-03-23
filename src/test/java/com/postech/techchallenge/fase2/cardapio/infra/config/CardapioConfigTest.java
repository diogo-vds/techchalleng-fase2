package com.postech.techchallenge.fase2.cardapio.infra.config;

import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;
import com.postech.techchallenge.fase2.cardapio.core.usecase.AtualizarCardapioUseCase;
import com.postech.techchallenge.fase2.cardapio.core.usecase.BuscarCardapioPorIdUseCase;
import com.postech.techchallenge.fase2.cardapio.core.usecase.CriarCardapioUseCase;
import com.postech.techchallenge.fase2.cardapio.core.usecase.DeletarCardapioUseCase;
import com.postech.techchallenge.fase2.cardapio.core.usecase.ListarCardapioUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

class CardapioConfigTest {

    private CardapioConfig config;
    private CardapioGateway cardapioGateway;

    @BeforeEach
    void setup() {
        config = new CardapioConfig();
        cardapioGateway = mock(CardapioGateway.class);
    }

    @Test
    void deveCriarBeanCriarCardapioUseCase() throws Exception {
        CriarCardapioUseCase useCase = config.criarCardapioUseCase(cardapioGateway);

        assertNotNull(useCase);
        assertSame(cardapioGateway, obterGateway(useCase));
    }

    @Test
    void deveCriarBeanAtualizarCardapioUseCase() throws Exception {
        AtualizarCardapioUseCase useCase = config.atualizarCardapioUseCase(cardapioGateway);

        assertNotNull(useCase);
        assertSame(cardapioGateway, obterGateway(useCase));
    }

    @Test
    void deveCriarBeanDeletarCardapioUseCase() throws Exception {
        DeletarCardapioUseCase useCase = config.deletarCardapioUseCase(cardapioGateway);

        assertNotNull(useCase);
        assertSame(cardapioGateway, obterGateway(useCase));
    }

    @Test
    void deveCriarBeanBuscarCardapioPorIdUseCase() throws Exception {
        BuscarCardapioPorIdUseCase useCase = config.buscarCardapioPorIdUseCase(cardapioGateway);

        assertNotNull(useCase);
        assertSame(cardapioGateway, obterGateway(useCase));
    }

    @Test
    void deveCriarBeanListarCardapioUseCase() throws Exception {
        ListarCardapioUseCase useCase = config.listarCardapioUseCase(cardapioGateway);

        assertNotNull(useCase);
        assertSame(cardapioGateway, obterGateway(useCase));
    }

    private Object obterGateway(Object useCase) throws Exception {
        Field field = useCase.getClass().getDeclaredField("cardapioGateway");
        field.setAccessible(true);
        return field.get(useCase);
    }
}
