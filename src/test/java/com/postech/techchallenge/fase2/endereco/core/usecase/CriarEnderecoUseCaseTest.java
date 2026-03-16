package com.postech.techchallenge.fase2.endereco.core.usecase;

import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoInput;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoOutput;
import com.postech.techchallenge.fase2.endereco.core.gateway.EnderecoGateway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CriarEnderecoUseCaseTest {

    private EnderecoGateway enderecoGateway;
    private CriarEnderecoUseCase useCase;

    @BeforeEach
    void setup() {
        enderecoGateway = mock(EnderecoGateway.class);
        useCase = new CriarEnderecoUseCase(enderecoGateway);
    }

    @Test
    void deveCriarEnderecoComSucesso() {

        EnderecoInput input = new EnderecoInput(
                null,
                "Rua das Flores",
                "123",
                "Apto 10",
                "Centro",
                "Curitiba",
                "PR",
                "80000000"
        );

        Endereco enderecoSalvo = Endereco.reconstruir(
                1L,
                "Rua das Flores",
                "123",
                "Apto 10",
                "Centro",
                "Curitiba",
                "PR",
                "80000000"
        );

        when(enderecoGateway.salvar(any(Endereco.class)))
                .thenReturn(enderecoSalvo);

        EnderecoOutput output = useCase.executar(input);

        assertNotNull(output);
        assertEquals(1L, output.id());
        assertEquals("Rua das Flores", output.rua());
        assertEquals("123", output.numero());
        assertEquals("Apto 10", output.complemento());
        assertEquals("Centro", output.bairro());
        assertEquals("Curitiba", output.cidade());
        assertEquals("PR", output.uf());
        assertEquals("80000000", output.cep());

        verify(enderecoGateway, times(1)).salvar(any(Endereco.class));
    }

    @Test
    void deveLancarExcecaoQuandoInputForNulo() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(null)
        );

        assertEquals("Input não pode ser nulo", exception.getMessage());

        verify(enderecoGateway, never()).salvar(any());
    }

    @Test
    void deveLancarExcecaoQuandoIdForInformadoNaCriacao() {

        EnderecoInput input = new EnderecoInput(
                10L,
                "Rua das Flores",
                "123",
                null,
                "Centro",
                "Curitiba",
                "PR",
                "80000000"
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(input)
        );

        assertEquals("Id não deve ser informado para criação", exception.getMessage());

        verify(enderecoGateway, never()).salvar(any());
    }

    @Test
    void devePropagarExcecaoQuandoDominioForInvalido() {

        EnderecoInput input = new EnderecoInput(
                null,
                "", // rua inválida
                "123",
                null,
                "Centro",
                "Curitiba",
                "PR",
                "80000000"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(input)
        );

        verify(enderecoGateway, never()).salvar(any());
    }

}