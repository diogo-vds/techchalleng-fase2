package com.postech.techchallenge.fase2.endereco.infra.config;

import com.postech.techchallenge.fase2.endereco.core.gateway.EnderecoGateway;
import com.postech.techchallenge.fase2.endereco.core.usecase.AtualizarEnderecoUseCase;
import com.postech.techchallenge.fase2.endereco.core.usecase.BuscarEnderecoPorIdUseCase;
import com.postech.techchallenge.fase2.endereco.core.usecase.CriarEnderecoUseCase;
import com.postech.techchallenge.fase2.endereco.core.usecase.DeletarEnderecoUseCase;
import com.postech.techchallenge.fase2.endereco.core.usecase.ListarEnderecosUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

class EnderecoConfigTest {

    private EnderecoConfig config;
    private EnderecoGateway enderecoGateway;

    @BeforeEach
    void setup() {
        config = new EnderecoConfig();
        enderecoGateway = mock(EnderecoGateway.class);
    }

    @Test
    void deveCriarBeanCriarEnderecoUseCase() throws Exception {
        CriarEnderecoUseCase useCase = config.criarEnderecoUseCase(enderecoGateway);

        assertNotNull(useCase);
        assertSame(enderecoGateway, obterGateway(useCase));
    }

    @Test
    void deveCriarBeanAtualizarEnderecoUseCase() throws Exception {
        AtualizarEnderecoUseCase useCase = config.atualizarEnderecoUseCase(enderecoGateway);

        assertNotNull(useCase);
        assertSame(enderecoGateway, obterGateway(useCase));
    }

    @Test
    void deveCriarBeanDeletarEnderecoUseCase() throws Exception {
        DeletarEnderecoUseCase useCase = config.deletarEnderecoUseCase(enderecoGateway);

        assertNotNull(useCase);
        assertSame(enderecoGateway, obterGateway(useCase));
    }

    @Test
    void deveCriarBeanBuscarEnderecoPorIdUseCase() throws Exception {
        BuscarEnderecoPorIdUseCase useCase = config.buscarEnderecoPorIdUseCase(enderecoGateway);

        assertNotNull(useCase);
        assertSame(enderecoGateway, obterGateway(useCase));
    }

    @Test
    void deveCriarBeanListarEnderecosUseCase() throws Exception {
        ListarEnderecosUseCase useCase = config.listarEnderecosUseCase(enderecoGateway);

        assertNotNull(useCase);
        assertSame(enderecoGateway, obterGateway(useCase));
    }

    private Object obterGateway(Object useCase) throws Exception {
        Field field = useCase.getClass().getDeclaredField("enderecoGateway");
        field.setAccessible(true);
        return field.get(useCase);
    }
}
