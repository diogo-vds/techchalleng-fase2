package com.postech.techchallenge.fase2.endereco.infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoInput;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoOutput;
import com.postech.techchallenge.fase2.endereco.core.usecase.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnderecoController.class)
class EnderecoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CriarEnderecoUseCase criarEnderecoUseCase;

    @MockitoBean
    private AtualizarEnderecoUseCase atualizarEnderecoUseCase;

    @MockitoBean
    private DeletarEnderecoUseCase deletarEnderecoUseCase;

    @MockitoBean
    private BuscarEnderecoPorIdUseCase buscarEnderecoPorIdUseCase;

    @MockitoBean
    private ListarEnderecosUseCase listarEnderecosUseCase;

    @Test
    void deveCriarEndereco() throws Exception {

        EnderecoInput input = new EnderecoInput(
                null,"Rua A","10",null,"Centro","Curitiba","PR","80000000"
        );

        EnderecoOutput output = new EnderecoOutput(
                1L,"Rua A","10",null,"Centro","Curitiba","PR","80000000"
        );

        when(criarEnderecoUseCase.executar(Mockito.any()))
                .thenReturn(output);

        mockMvc.perform(post("/enderecos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarEnderecoPorId() throws Exception {

        EnderecoOutput output = new EnderecoOutput(
                1L,"Rua A","10",null,"Centro","Curitiba","PR","80000000"
        );

        when(buscarEnderecoPorIdUseCase.executar(1L))
                .thenReturn(output);

        mockMvc.perform(get("/enderecos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deveListarEnderecos() throws Exception {

        EnderecoOutput output = new EnderecoOutput(
                1L,"Rua A","10",null,"Centro","Curitiba","PR","80000000"
        );

        when(listarEnderecosUseCase.executar())
                .thenReturn(List.of(output));

        mockMvc.perform(get("/enderecos"))
                .andExpect(status().isOk());
    }

    @Test
    void deveAtualizarEndereco() throws Exception {

        EnderecoInput input = new EnderecoInput(
                null,"Rua B","20",null,"Centro","Curitiba","PR","80000000"
        );

        EnderecoOutput output = new EnderecoOutput(
                1L,"Rua B","20",null,"Centro","Curitiba","PR","80000000"
        );

        when(atualizarEnderecoUseCase.executar(Mockito.any()))
                .thenReturn(output);

        mockMvc.perform(put("/enderecos/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @Test
    void deveDeletarEndereco() throws Exception {

        mockMvc.perform(delete("/enderecos/1"))
                .andExpect(status().isNoContent());
    }
}