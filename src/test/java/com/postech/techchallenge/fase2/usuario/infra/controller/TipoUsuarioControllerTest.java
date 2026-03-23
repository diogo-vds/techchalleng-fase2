package com.postech.techchallenge.fase2.usuario.infra.controller;

import com.postech.techchallenge.fase2.restaurante.infra.controller.GlobalExceptionHandler;
import com.postech.techchallenge.fase2.usuario.core.dto.AtualizarTipoUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.dto.CriarTipoUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.dto.DeletarTipoUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.dto.TipoUsuarioOutput;
import com.postech.techchallenge.fase2.usuario.core.usecase.AtualizarTipoUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.BuscarTipoUsuarioPorIdUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.CriarTipoUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.DeletarTipoUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.ListarTiposUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.infra.controller.dto.TipoUsuarioRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TipoUsuarioController.class)
@Import(GlobalExceptionHandler.class)
class TipoUsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CriarTipoUsuarioUseCase criarTipoUsuarioUseCase;

    @MockBean
    private AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase;

    @MockBean
    private BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase;

    @MockBean
    private ListarTiposUsuarioUseCase listarTiposUsuarioUseCase;

    @MockBean
    private DeletarTipoUsuarioUseCase deletarTipoUsuarioUseCase;

    @Test
    void deveCriarTipoUsuario() throws Exception {
        TipoUsuarioRequest request = new TipoUsuarioRequest("CLIENTE");

        when(criarTipoUsuarioUseCase.executar(any(CriarTipoUsuarioInput.class)))
                .thenReturn(new TipoUsuarioOutput(1L, "CLIENTE"));

        mockMvc.perform(post("/tipos-usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.descricao").value("CLIENTE"));
    }

    @Test
    void deveRetornarBadRequestQuandoTipoUsuarioJaExistirAoCriar() throws Exception {
        TipoUsuarioRequest request = new TipoUsuarioRequest("CLIENTE");

        when(criarTipoUsuarioUseCase.executar(any(CriarTipoUsuarioInput.class)))
                .thenThrow(new IllegalArgumentException("Tipo de usuario 'CLIENTE' ja cadastrado"));

                mockMvc.perform(post("/tipos-usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Tipo de usuario 'CLIENTE' ja cadastrado"));
    }

    @Test
    void deveAtualizarTipoUsuario() throws Exception {
        TipoUsuarioRequest request = new TipoUsuarioRequest("ADMIN");

        when(atualizarTipoUsuarioUseCase.executar(any(AtualizarTipoUsuarioInput.class)))
                .thenReturn(new TipoUsuarioOutput(1L, "ADMIN"));

        mockMvc.perform(put("/tipos-usuario/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao").value("ADMIN"));
    }

    @Test
    void deveBuscarTipoUsuarioPorId() throws Exception {
        when(buscarTipoUsuarioPorIdUseCase.executar(1L))
                .thenReturn(new TipoUsuarioOutput(1L, "CLIENTE"));

        mockMvc.perform(get("/tipos-usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.descricao").value("CLIENTE"));
    }

    @Test
    void deveListarTiposUsuario() throws Exception {
        when(listarTiposUsuarioUseCase.executar())
                .thenReturn(List.of(
                        new TipoUsuarioOutput(1L, "CLIENTE"),
                        new TipoUsuarioOutput(2L, "DONO_RESTAURANTE")
                ));

        mockMvc.perform(get("/tipos-usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].descricao").value("CLIENTE"))
                .andExpect(jsonPath("$[1].descricao").value("DONO_RESTAURANTE"));
    }

    @Test
    void deveRetornarBadRequestQuandoTipoUsuarioNaoEncontrado() throws Exception {
        when(buscarTipoUsuarioPorIdUseCase.executar(99L))
                .thenThrow(new IllegalArgumentException("Tipo de usuario nao encontrado"));

        mockMvc.perform(get("/tipos-usuario/99"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Tipo de usuario nao encontrado"));
    }

    @Test
    void deveDeletarTipoUsuario() throws Exception {
        doNothing().when(deletarTipoUsuarioUseCase).executar(any(DeletarTipoUsuarioInput.class));

        mockMvc.perform(delete("/tipos-usuario/1"))
                .andExpect(status().isNoContent());
    }
}
