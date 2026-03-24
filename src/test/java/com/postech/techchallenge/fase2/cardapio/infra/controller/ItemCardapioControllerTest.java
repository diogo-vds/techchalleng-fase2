package com.postech.techchallenge.fase2.cardapio.infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioInput;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.usecase.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemCardapioController.class)
class ItemCardapioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CriarItemCardapioUseCase criarItemCardapioUseCase;

    @MockitoBean
    private AtualizarItemCardapioUseCase atualizarItemCardapioUseCase;

    @MockitoBean
    private DeletarItemCardapioUseCase deletarItemCardapioUseCase;

    @MockitoBean
    private BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase;

    @MockitoBean
    private ListarItemCardapioUseCase listarItemCardapioUseCase;

    @Test
    void deveCriarItemCardapio() throws Exception {
        ItemCardapioInput input = new ItemCardapioInput(
                null, 1L, "Batata Frita", "Porção de batata", new BigDecimal("15.00"), true, "/foto.jpg"
        );

        ItemCardapioOutput output = new ItemCardapioOutput(
                1L, 1L, "Batata Frita", "Porção de batata", new BigDecimal("15.00"), true, "/foto.jpg"
        );

        when(criarItemCardapioUseCase.executar(Mockito.any())).thenReturn(output);

        mockMvc.perform(post("/item-cardapio")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarItemCardapioPorId() throws Exception {
        ItemCardapioOutput output = new ItemCardapioOutput(
                1L, 1L, "Batata Frita", "Porção de batata", new BigDecimal("15.00"), true, "/foto.jpg"
        );

        when(buscarItemCardapioPorIdUseCase.executar(1L)).thenReturn(output);

        mockMvc.perform(get("/item-cardapio/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deveListarItemCardapios() throws Exception {
        ItemCardapioOutput output = new ItemCardapioOutput(
                1L, 1L, "Batata Frita", "Porção de batata", new BigDecimal("15.00"), true, "/foto.jpg"
        );

        when(listarItemCardapioUseCase.executar()).thenReturn(List.of(output));

        mockMvc.perform(get("/item-cardapio"))
                .andExpect(status().isOk());
    }

    @Test
    void deveAtualizarItemCardapio() throws Exception {
        ItemCardapioInput input = new ItemCardapioInput(
                null, 1L, "Batata Frita Atualizada", "Porção grande", new BigDecimal("20.00"), false, "/nova.jpg"
        );

        ItemCardapioOutput output = new ItemCardapioOutput(
                1L, 1L, "Batata Frita Atualizada", "Porção grande", new BigDecimal("20.00"), false, "/nova.jpg"
        );

        when(atualizarItemCardapioUseCase.executar(Mockito.any())).thenReturn(output);

        mockMvc.perform(put("/item-cardapio/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @Test
    void deveDeletarItemCardapio() throws Exception {
        mockMvc.perform(delete("/item-cardapio/1"))
                .andExpect(status().isNoContent());
    }
}
