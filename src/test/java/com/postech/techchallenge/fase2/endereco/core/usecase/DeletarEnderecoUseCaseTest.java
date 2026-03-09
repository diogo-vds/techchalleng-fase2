package com.postech.techchallenge.fase2.endereco.core.usecase;

import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.endereco.core.gateway.EnderecoGateway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeletarEnderecoUseCaseTest {

    private EnderecoGateway enderecoGateway;
    private DeletarEnderecoUseCase useCase;

    @BeforeEach
    void setup() {
        enderecoGateway = mock(EnderecoGateway.class);
        useCase = new DeletarEnderecoUseCase(enderecoGateway);
    }

    @Test
    void deveDeletarEnderecoComSucesso() {

        Long id = 1L;

        Endereco endereco = Endereco.reconstruir(
                1L,
                "Rua das Flores",
                "123",
                null,
                "Centro",
                "Curitiba",
                "PR",
                "80000000"
        );

        when(enderecoGateway.buscarPorId(id))
                .thenReturn(Optional.of(endereco));

        doNothing().when(enderecoGateway).deletar(id);

        assertDoesNotThrow(() -> useCase.executar(id));

        verify(enderecoGateway, times(1)).buscarPorId(id);
        verify(enderecoGateway, times(1)).deletar(id);
    }

    @Test
    void deveLancarExcecaoQuandoIdForNulo() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(null)
        );

        assertEquals("Id não pode ser nulo", exception.getMessage());

        verify(enderecoGateway, never()).buscarPorId(any());
        verify(enderecoGateway, never()).deletar(any());
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
        verify(enderecoGateway, never()).deletar(any());
    }

}