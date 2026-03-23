package com.postech.techchallenge.fase2.restaurante.infra.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler handler;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveTratarIllegalArgumentException() {

        String mensagem = "Erro de validação";

        IllegalArgumentException exception = new IllegalArgumentException(mensagem);

        ResponseEntity<Map<String, Object>> response =
                handler.handleIllegalArgumentException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        Map<String, Object> body = response.getBody();

        assertNotNull(body);
        assertEquals(400, body.get("status"));
        assertEquals("Bad Request", body.get("error"));
        assertEquals(mensagem, body.get("message"));
        assertNotNull(body.get("timestamp"));
    }

    @Test
    void deveRetornarNotFoundQuandoMensagemContemNaoEncontrado() {

        RuntimeException exception = new RuntimeException("Restaurante não encontrado");

        ResponseEntity<Map<String, Object>> response =
                handler.handleRuntimeException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        Map<String, Object> body = response.getBody();

        assertNotNull(body);
        assertEquals(404, body.get("status"));
        assertEquals("Not Found", body.get("error"));
        assertEquals("Restaurante não encontrado", body.get("message"));
    }

    @Test
    void deveRetornarInternalServerErrorParaOutrosErros() {

        RuntimeException exception = new RuntimeException("Erro inesperado");

        ResponseEntity<Map<String, Object>> response =
                handler.handleRuntimeException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        Map<String, Object> body = response.getBody();

        assertNotNull(body);
        assertEquals(500, body.get("status"));
        assertEquals("Internal Server Error", body.get("error"));
        assertEquals("Erro inesperado", body.get("message"));
    }
}