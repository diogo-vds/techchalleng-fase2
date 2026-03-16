package com.postech.techchallenge.fase2.endereco.core.usecase;

import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoOutput;
import com.postech.techchallenge.fase2.endereco.core.gateway.EnderecoGateway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListarEnderecosUseCaseTest {

    private EnderecoGateway enderecoGateway;
    private ListarEnderecosUseCase useCase;

    @BeforeEach
    void setup() {
        enderecoGateway = mock(EnderecoGateway.class);
        useCase = new ListarEnderecosUseCase(enderecoGateway);
    }

    @Test
    void deveListarEnderecosComSucesso() {

        Endereco endereco1 = Endereco.reconstruir(
                1L,
                "Rua das Flores",
                "123",
                "Apto 10",
                "Centro",
                "Curitiba",
                "PR",
                "80000000"
        );

        Endereco endereco2 = Endereco.reconstruir(
                2L,
                "Av. Brasil",
                "500",
                null,
                "Batel",
                "Curitiba",
                "PR",
                "80010000"
        );

        when(enderecoGateway.listarTodos())
                .thenReturn(List.of(endereco1, endereco2));

        List<EnderecoOutput> resultado = useCase.executar();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        EnderecoOutput primeiro = resultado.get(0);

        assertEquals(1L, primeiro.id());
        assertEquals("Rua das Flores", primeiro.rua());
        assertEquals("123", primeiro.numero());
        assertEquals("Apto 10", primeiro.complemento());
        assertEquals("Centro", primeiro.bairro());
        assertEquals("Curitiba", primeiro.cidade());
        assertEquals("PR", primeiro.uf());
        assertEquals("80000000", primeiro.cep());

        verify(enderecoGateway, times(1)).listarTodos();
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremEnderecos() {

        when(enderecoGateway.listarTodos())
                .thenReturn(List.of());

        List<EnderecoOutput> resultado = useCase.executar();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

        verify(enderecoGateway, times(1)).listarTodos();
    }
}