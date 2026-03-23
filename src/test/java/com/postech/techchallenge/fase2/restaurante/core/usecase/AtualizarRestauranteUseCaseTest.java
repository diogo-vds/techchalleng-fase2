package com.postech.techchallenge.fase2.restaurante.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarRestauranteUseCaseTest {

    private RestauranteGateway gateway;
    private AtualizarRestauranteUseCase useCase;

    @BeforeEach
    void setup() {
        gateway = mock(RestauranteGateway.class);
        useCase = new AtualizarRestauranteUseCase(gateway);
    }

    private Endereco criarEndereco() {
        return Endereco.criar(
                "Rua A", "123", null,
                "Centro", "São Paulo", "SP", "12345678"
        );
    }

    private Cardapio criarCardapio() {
        return Cardapio.criar(
                "Pizza",
                "Descrição",
                new BigDecimal("50.00"),
                true,
                "/img.png"
        );
    }

    @Test
    void deveAtualizarRestauranteComSucesso() {

        Long id = 1L;

        Restaurante restaurante = new Restaurante(
                id,
                "Antigo",
                criarEndereco(),
                "Brasileira",
                criarCardapio(),
                "09:00",
                10L
        );

        when(gateway.buscarPorId(id)).thenReturn(Optional.of(restaurante));
        when(gateway.salvar(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Endereco novoEndereco = Endereco.criar(
                "Rua Nova", "999", null,
                "Centro", "Rio", "RJ", "87654321"
        );

        Cardapio novoCardapio = Cardapio.criar(
                "Sushi",
                "Descrição",
                new BigDecimal("100.00"),
                false,
                "/sushi.png"
        );

        Restaurante resultado = useCase.executar(
                id,
                "Novo Nome",
                novoEndereco,
                "Japonesa",
                novoCardapio,
                "10:00 - 23:00"
        );

        assertEquals("Novo Nome", resultado.getNome());
        assertEquals(novoEndereco, resultado.getEndereco());
        assertEquals("Japonesa", resultado.getTipoCozinha());
        assertEquals(novoCardapio, resultado.getCardapio());
        assertEquals("10:00 - 23:00", resultado.getHorarioFuncionamento());

        verify(gateway).buscarPorId(id);
        verify(gateway).salvar(restaurante);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {

        Long id = 1L;

        when(gateway.buscarPorId(id)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                useCase.executar(
                        id,
                        "Nome",
                        criarEndereco(),
                        "Italiana",
                        criarCardapio(),
                        "08:00"
                )
        );

        assertEquals("Restaurante não encontrado", ex.getMessage());

        verify(gateway).buscarPorId(id);
        verify(gateway, never()).salvar(any());
    }
}