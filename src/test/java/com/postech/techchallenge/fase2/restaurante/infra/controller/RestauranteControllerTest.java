package com.postech.techchallenge.fase2.restaurante.infra.controller;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import com.postech.techchallenge.fase2.restaurante.core.usecase.*;
import com.postech.techchallenge.fase2.restaurante.infra.controller.dto.RestauranteRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestauranteControllerTest {

    @Mock private CriarRestauranteUseCase criarUseCase;
    @Mock private RecuperarRestauranteUseCase recuperarUseCase;
    @Mock private AtualizarRestauranteUseCase atualizarUseCase;
    @Mock private DeletarRestauranteUseCase deletarUseCase;

    @InjectMocks
    private RestauranteController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private Endereco endereco() {
        return Endereco.criar("Rua", "1", null, "Centro", "SP", "SP", "12345678");
    }

    private Cardapio cardapio() {
        return Cardapio.criar("Pizza", "Desc", Collections.emptyList());
    }

    private Restaurante restaurante() {
        return new Restaurante(1L, "Nome", endereco(), "Italiana", cardapio(), "08:00", 1L);
    }

    private RestauranteRequestDTO request() {
        return new RestauranteRequestDTO("Nome", endereco(), "Italiana", cardapio(), "08:00", 1L);
    }

    @Test
    void criar_sucesso() {
        when(criarUseCase.executar(any(), any(), any(), any(), any(), any()))
                .thenReturn(restaurante());

        var response = controller.criar(request());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void criar_badRequest() {
        when(criarUseCase.executar(any(), any(), any(), any(), any(), any()))
                .thenThrow(new IllegalArgumentException());

        var response = controller.criar(request());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void criar_internalError() {
        when(criarUseCase.executar(any(), any(), any(), any(), any(), any()))
                .thenThrow(new RuntimeException());

        var response = controller.criar(request());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void buscar_sucesso() {
        when(recuperarUseCase.porId(1L)).thenReturn(restaurante());

        var response = controller.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscar_badRequest() {
        when(recuperarUseCase.porId(1L)).thenThrow(new IllegalArgumentException());

        var response = controller.buscarPorId(1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void buscar_notFound() {
        when(recuperarUseCase.porId(1L))
                .thenThrow(new RuntimeException("não encontrado"));

        var response = controller.buscarPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void buscar_internalError() {
        when(recuperarUseCase.porId(1L))
                .thenThrow(new RuntimeException("erro"));

        var response = controller.buscarPorId(1L);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void listar_sucesso() {
        when(recuperarUseCase.todos()).thenReturn(List.of(restaurante()));

        var response = controller.listarTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listar_erro() {
        when(recuperarUseCase.todos()).thenThrow(new RuntimeException());

        var response = controller.listarTodos();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void atualizar_sucesso() {
        when(atualizarUseCase.executar(any(), any(), any(), any(), any(), any()))
                .thenReturn(restaurante());

        var response = controller.atualizar(1L, request());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void atualizar_badRequest() {
        when(atualizarUseCase.executar(any(), any(), any(), any(), any(), any()))
                .thenThrow(new IllegalArgumentException());

        var response = controller.atualizar(1L, request());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void atualizar_notFound() {
        when(atualizarUseCase.executar(any(), any(), any(), any(), any(), any()))
                .thenThrow(new RuntimeException("não encontrado"));

        var response = controller.atualizar(1L, request());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void atualizar_internalError() {
        when(atualizarUseCase.executar(any(), any(), any(), any(), any(), any()))
                .thenThrow(new RuntimeException("erro"));

        var response = controller.atualizar(1L, request());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void deletar_sucesso() {
        var response = controller.deletar(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deletarUseCase).executar(1L);
    }

    @Test
    void deletar_badRequest() {
        doThrow(new IllegalArgumentException()).when(deletarUseCase).executar(1L);

        var response = controller.deletar(1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deletar_notFound() {
        doThrow(new RuntimeException("não encontrado"))
                .when(deletarUseCase).executar(1L);

        var response = controller.deletar(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deletar_internalError() {
        doThrow(new RuntimeException("erro"))
                .when(deletarUseCase).executar(1L);

        var response = controller.deletar(1L);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}