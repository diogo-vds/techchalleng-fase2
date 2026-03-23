package com.postech.techchallenge.fase2.restaurante.core.usecase;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CriarRestauranteUseCaseTest {

    private RestauranteGateway gateway;
    private CriarRestauranteUseCase useCase;

    @BeforeEach
    void setup() {
        gateway = mock(RestauranteGateway.class);
        useCase = new CriarRestauranteUseCase(gateway);
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
    void deveCriarRestauranteComSucesso() {

        when(gateway.salvar(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Restaurante resultado = useCase.executar(
                "Restaurante Teste",
                criarEndereco(),
                "Italiana",
                criarCardapio(),
                "08:00 - 22:00",
                1L
        );

        assertNotNull(resultado);
        assertEquals("Restaurante Teste", resultado.getNome());
        assertEquals("Italiana", resultado.getTipoCozinha());
        assertEquals("08:00 - 22:00", resultado.getHorarioFuncionamento());
        assertEquals(1L, resultado.getDonoId());

        verify(gateway).salvar(any(Restaurante.class));
    }

    @Test
    void deveFalharQuandoNomeForNulo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                useCase.executar(
                        null,
                        criarEndereco(),
                        "Italiana",
                        criarCardapio(),
                        "08:00",
                        1L
                )
        );

        assertEquals("Nome do restaurante é obrigatório", ex.getMessage());
        verify(gateway, never()).salvar(any());
    }

    @Test
    void deveFalharQuandoNomeForVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                useCase.executar(
                        "   ",
                        criarEndereco(),
                        "Italiana",
                        criarCardapio(),
                        "08:00",
                        1L
                )
        );
    }

    @Test
    void deveFalharQuandoEnderecoForNulo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                useCase.executar(
                        "Nome",
                        null,
                        "Italiana",
                        criarCardapio(),
                        "08:00",
                        1L
                )
        );

        assertEquals("Endereço do restaurante é obrigatório", ex.getMessage());
    }

    @Test
    void deveFalharQuandoTipoCozinhaForInvalido() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                useCase.executar(
                        "Nome",
                        criarEndereco(),
                        "   ",
                        criarCardapio(),
                        "08:00",
                        1L
                )
        );

        assertEquals("Tipo de cozinha é obrigatório", ex.getMessage());
    }

    @Test
    void deveFalharQuandoTipoCozinhaForNulo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                useCase.executar(
                        "Nome",
                        criarEndereco(),
                        null,
                        criarCardapio(),
                        "08:00",
                        1L
                )
        );

        assertEquals("Tipo de cozinha \u00e9 obrigat\u00f3rio", ex.getMessage());
        verify(gateway, never()).salvar(any());
    }

    @Test
    void deveFalharQuandoHorarioForInvalido() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                useCase.executar(
                        "Nome",
                        criarEndereco(),
                        "Italiana",
                        criarCardapio(),
                        "   ",
                        1L
                )
        );

        assertEquals("Horário de funcionamento é obrigatório", ex.getMessage());
    }

    @Test
    void deveFalharQuandoHorarioForNulo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                useCase.executar(
                        "Nome",
                        criarEndereco(),
                        "Italiana",
                        criarCardapio(),
                        null,
                        1L
                )
        );

        assertEquals("Hor\u00e1rio de funcionamento \u00e9 obrigat\u00f3rio", ex.getMessage());
        verify(gateway, never()).salvar(any());
    }

    @Test
    void deveFalharQuandoDonoIdForNulo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                useCase.executar(
                        "Nome",
                        criarEndereco(),
                        "Italiana",
                        criarCardapio(),
                        "08:00",
                        null
                )
        );

        assertEquals("ID do dono é obrigatório", ex.getMessage());
    }
}
