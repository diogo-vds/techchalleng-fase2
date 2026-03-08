package com.postech.techchallenge.fase2.usuario.infra.controller;

import com.postech.techchallenge.fase2.usuario.core.dto.*;
import com.postech.techchallenge.fase2.usuario.core.usecase.*;
import com.postech.techchallenge.fase2.usuario.infra.controller.dto.UsuarioRequest;
import com.postech.techchallenge.fase2.usuario.infra.controller.dto.UsuarioResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CriarUsuarioUseCase criarUsuarioUseCase;

    @MockBean
    private AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    @MockBean
    private BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    @MockBean
    private ListarUsuariosUseCase listarUsuariosUseCase;

    @MockBean
    private DeletarUsuarioUseCase deletarUsuarioUseCase;

    @Test
    void deveCriarUsuario() throws Exception {
        UsuarioRequest request = new UsuarioRequest("João", "joao@email.com", "41999999999", "12345678900", 1L);
        UsuarioOutput output = new UsuarioOutput(1L, "João", "joao@email.com", "41999999999", "12345678900", "ADMIN");

        when(criarUsuarioUseCase.executar(any(CriarUsuarioInput.class))).thenReturn(output);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("João"));
    }

    @Test
    void deveAtualizarUsuario() throws Exception {
        UsuarioRequest request = new UsuarioRequest("João Atualizado", "joao@email.com", "41999999999", "12345678900", 1L);
        UsuarioOutput output = new UsuarioOutput(1L, "João Atualizado", "joao@email.com", "41999999999", "12345678900", "ADMIN");

        when(atualizarUsuarioUseCase.executar(any(AtualizarUsuarioInput.class))).thenReturn(output);

        mockMvc.perform(put("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Atualizado"));
    }

    @Test
    void deveBuscarUsuarioPorId() throws Exception {
        UsuarioOutput output = new UsuarioOutput(1L, "João", "joao@email.com", "41999999999", "12345678900", "ADMIN");

        when(buscarUsuarioPorIdUseCase.executar(any(BuscarUsuarioInput.class))).thenReturn(output);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void deveListarUsuarios() throws Exception {
        UsuarioOutput output1 = new UsuarioOutput(1L, "João", "joao@email.com", "41999999999", "12345678900", "ADMIN");
        UsuarioOutput output2 = new UsuarioOutput(2L, "Maria", "maria@email.com", "41888888888", "98765432100", "CLIENTE");

        when(listarUsuariosUseCase.executar()).thenReturn(List.of(output1, output2));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value("João"))
                .andExpect(jsonPath("$[1].nome").value("Maria"));
    }

    @Test
    void deveDeletarUsuario() throws Exception {
        doNothing().when(deletarUsuarioUseCase).executar(any(DeletarUsuarioInput.class));

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isNoContent());
    }
}
