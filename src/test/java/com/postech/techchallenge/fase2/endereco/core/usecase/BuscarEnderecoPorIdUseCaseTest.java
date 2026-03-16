package com.postech.techchallenge.fase2.endereco.core.usecase;

import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoOutput;
import com.postech.techchallenge.fase2.endereco.core.gateway.EnderecoGateway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarEnderecoPorIdUseCaseTest {

    private EnderecoGateway enderecoGateway;
    private BuscarEnderecoPorIdUseCase useCase;

    @BeforeEach
    void setup() {
        enderecoGateway = mock(EnderecoGateway.class);
        useCase = new BuscarEnderecoPorIdUseCase(enderecoGateway);
    }

    @Test
    void deveBuscarEnderecoPorIdComSucesso() {

        Long id = 1L;

        Endereco endereco = Endereco.reconstruir(
                1L,
                "Rua das Flores",
                "123",
                "Apto 10",
                "Centro",
                "Curitiba",
                "PR",
                "80000000"
        );

        when(enderecoGateway.buscarPorId(id))
                .thenReturn(Optional.of(endereco));

        EnderecoOutput resultado = useCase.executar(id);

        assertNotNull(resultado);
        assertEquals(1L, resultado.id());
        assertEquals("Rua das Flores", resultado.rua());
        assertEquals("123", resultado.numero());
        assertEquals("Apto 10", resultado.complemento());
        assertEquals("Centro", resultado.bairro());
        assertEquals("Curitiba", resultado.cidade());
        assertEquals("PR", resultado.uf());
        assertEquals("80000000", resultado.cep());

        verify(enderecoGateway, times(1)).buscarPorId(id);
    }

    @Test
    void deveLancarExcecaoQuandoIdForNulo() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(null)
        );

        assertEquals("Id não pode ser nulo", exception.getMessage());

        verify(enderecoGateway, never()).buscarPorId(any());
    }

    @Test
    void deveLancarExcecaoQuandoEnderecoNaoExistir() {

        Long id = 1L;

        when(enderecoGateway.buscarPorId(id))
                .thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(id)
        );

        assertEquals("Endereço não encontrado", exception.getMessage());

        verify(enderecoGateway, times(1)).buscarPorId(id);
    }
}