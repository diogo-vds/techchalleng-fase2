package com.postech.techchallenge.fase2.endereco.core.usecase;

import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoInput;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoOutput;
import com.postech.techchallenge.fase2.endereco.core.gateway.EnderecoGateway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarEnderecoUseCaseTest {

    private EnderecoGateway enderecoGateway;
    private AtualizarEnderecoUseCase useCase;

    @BeforeEach
    void setup() {
        enderecoGateway = Mockito.mock(EnderecoGateway.class);
        useCase = new AtualizarEnderecoUseCase(enderecoGateway);
    }

    @Test
    void deveAtualizarEnderecoComSucesso() {

        Endereco enderecoExistente = Endereco.reconstruir(
                1L,
                "Rua Antiga",
                "10",
                null,
                "Centro",
                "Curitiba",
                "PR",
                "80000000"
        );

        EnderecoInput input = new EnderecoInput(
                1L,
                "Rua Nova",
                "123",
                "Apto 1",
                "Centro",
                "Curitiba",
                "PR",
                "80000000"
        );

        Endereco enderecoAtualizado = Endereco.reconstruir(
                1L,
                "Rua Nova",
                "123",
                "Apto 1",
                "Centro",
                "Curitiba",
                "PR",
                "80000000"
        );

        when(enderecoGateway.buscarPorId(1L))
                .thenReturn(Optional.of(enderecoExistente));

        when(enderecoGateway.salvar(any(Endereco.class)))
                .thenReturn(enderecoAtualizado);

        EnderecoOutput output = useCase.executar(input);

        assertNotNull(output);
        assertEquals(1L, output.id());
        assertEquals("Rua Nova", output.rua());
        assertEquals("123", output.numero());
        assertEquals("Apto 1", output.complemento());
        assertEquals("Centro", output.bairro());
        assertEquals("Curitiba", output.cidade());
        assertEquals("PR", output.uf());
        assertEquals("80000000", output.cep());

        verify(enderecoGateway, times(1)).buscarPorId(1L);
        verify(enderecoGateway, times(1)).salvar(any(Endereco.class));
    }

    @Test
    void deveLancarExcecaoQuandoEnderecoNaoExistir() {

        EnderecoInput input = new EnderecoInput(
                1L,
                "Rua Nova",
                "123",
                null,
                "Centro",
                "Curitiba",
                "PR",
                "80000000"
        );

        when(enderecoGateway.buscarPorId(1L))
                .thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(input)
        );

        assertEquals("Endereço não encontrado", exception.getMessage());

        verify(enderecoGateway, times(1)).buscarPorId(1L);
        verify(enderecoGateway, never()).salvar(any());
    }

    @Test
    void deveLancarExcecaoQuandoInputForNulo() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(null)
        );

        assertEquals("Input não pode ser nulo", exception.getMessage());

        verify(enderecoGateway, never()).buscarPorId(any());
        verify(enderecoGateway, never()).salvar(any());
    }
}