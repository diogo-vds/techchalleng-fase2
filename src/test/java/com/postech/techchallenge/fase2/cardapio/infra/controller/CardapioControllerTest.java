package com.postech.techchallenge.fase2.cardapio.infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioInput;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioOutput;
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

@WebMvcTest(CardapioController.class)
class CardapioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CriarCardapioUseCase criarCardapioUseCase;

    @MockitoBean
    private AtualizarCardapioUseCase atualizarCardapioUseCase;

    @MockitoBean
    private DeletarCardapioUseCase deletarCardapioUseCase;

    @MockitoBean
    private BuscarCardapioPorIdUseCase buscarCardapioPorIdUseCase;

    @MockitoBean
    private ListarCardapioUseCase listarCardapioUseCase;

    @Test
    void deveCriarCardapio() throws Exception {
        ItemCardapioInput input = new ItemCardapioInput(
                null, "Hamburguer", "Delicioso", new BigDecimal("25.00"), true, "/foto.jpg"
        );

        CardapioOutput output = new CardapioOutput(
                1L, "Hamburguer", "Delicioso", new BigDecimal("25.00"), true, "/foto.jpg"
        );

        when(criarCardapioUseCase.executar(Mockito.any())).thenReturn(output);

        mockMvc.perform(post("/cardapio")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarCardapioPorId() throws Exception {
        CardapioOutput output = new CardapioOutput(
                1L, "Hamburguer", "Delicioso", new BigDecimal("25.00"), true, "/foto.jpg"
        );

        when(buscarCardapioPorIdUseCase.executar(1L)).thenReturn(output);

        mockMvc.perform(get("/cardapio/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deveListarCardapios() throws Exception {
        CardapioOutput output = new CardapioOutput(
                1L, "Hamburguer", "Delicioso", new BigDecimal("25.00"), true, "/foto.jpg"
        );

        when(listarCardapioUseCase.executar()).thenReturn(List.of(output));

        mockMvc.perform(get("/cardapio"))
                .andExpect(status().isOk());
    }

    @Test
    void deveAtualizarCardapio() throws Exception {
        ItemCardapioInput input = new ItemCardapioInput(
                null, "Hamburguer Atualizado", "Mais gostoso", new BigDecimal("30.00"), false, "/nova.jpg"
        );

        CardapioOutput output = new CardapioOutput(
                1L, "Hamburguer Atualizado", "Mais gostoso", new BigDecimal("30.00"), false, "/nova.jpg"
        );

        when(atualizarCardapioUseCase.executar(Mockito.any())).thenReturn(output);

        mockMvc.perform(put("/cardapio/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @Test
    void deveDeletarCardapio() throws Exception {
        mockMvc.perform(delete("/cardapio/1"))
                .andExpect(status().isNoContent());
    }
}
