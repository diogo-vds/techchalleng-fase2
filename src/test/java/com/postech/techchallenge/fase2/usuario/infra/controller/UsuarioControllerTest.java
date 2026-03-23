package com.postech.techchallenge.fase2.usuario.infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.techchallenge.fase2.restaurante.infra.controller.GlobalExceptionHandler;
import com.postech.techchallenge.fase2.usuario.core.dto.AtualizarUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.dto.BuscarUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.dto.CriarUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.dto.DeletarUsuarioInput;
import com.postech.techchallenge.fase2.usuario.core.dto.UsuarioOutput;
import com.postech.techchallenge.fase2.usuario.core.usecase.AtualizarUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.BuscarUsuarioPorIdUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.CriarUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.DeletarUsuarioUseCase;
import com.postech.techchallenge.fase2.usuario.core.usecase.ListarUsuariosUseCase;
import com.postech.techchallenge.fase2.usuario.infra.controller.dto.UsuarioRequest;
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

@WebMvcTest(UsuarioController.class)
@Import(GlobalExceptionHandler.class)
class UsuarioControllerTest {

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
        UsuarioRequest request = new UsuarioRequest("Joao", "joao@email.com", "41999999999", "12345678900", 1L);
        UsuarioOutput output = new UsuarioOutput(1L, "Joao", "joao@email.com", "41999999999", "12345678900", "ADMIN");

        when(criarUsuarioUseCase.executar(any(CriarUsuarioInput.class))).thenReturn(output);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Joao"));
    }

    @Test
    void deveRetornarBadRequestQuandoEmailJaExistirAoCriar() throws Exception {
        UsuarioRequest request = new UsuarioRequest("Joao", "joao@email.com", "41999999999", "12345678900", 1L);

        when(criarUsuarioUseCase.executar(any(CriarUsuarioInput.class)))
                .thenThrow(new IllegalArgumentException("Email 'joao@email.com' ja cadastrado"));

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Email 'joao@email.com' ja cadastrado"));
    }

    @Test
    void deveRetornarBadRequestQuandoCpfJaExistirAoCriar() throws Exception {
        UsuarioRequest request = new UsuarioRequest("Joao", "joao@email.com", "41999999999", "12345678900", 1L);

        when(criarUsuarioUseCase.executar(any(CriarUsuarioInput.class)))
                .thenThrow(new IllegalArgumentException("CPF '12345678900' ja cadastrado"));

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("CPF '12345678900' ja cadastrado"));
    }

    @Test
    void deveRetornarBadRequestQuandoTelefoneJaExistirAoCriar() throws Exception {
        UsuarioRequest request = new UsuarioRequest("Joao", "joao@email.com", "41999999999", "12345678900", 1L);

        when(criarUsuarioUseCase.executar(any(CriarUsuarioInput.class)))
                .thenThrow(new IllegalArgumentException("Telefone '41999999999' ja cadastrado"));

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Telefone '41999999999' ja cadastrado"));
    }

    @Test
    void deveAtualizarUsuario() throws Exception {
        UsuarioRequest request = new UsuarioRequest("Joao Atualizado", "joao@email.com", "41999999999", "12345678900", 1L);
        UsuarioOutput output = new UsuarioOutput(1L, "Joao Atualizado", "joao@email.com", "41999999999", "12345678900", "ADMIN");

        when(atualizarUsuarioUseCase.executar(any(AtualizarUsuarioInput.class))).thenReturn(output);

        mockMvc.perform(put("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Joao Atualizado"));
    }

    @Test
    void deveRetornarBadRequestQuandoEmailJaExistirAoAtualizar() throws Exception {
        UsuarioRequest request = new UsuarioRequest("Joao", "joao@email.com", "41999999999", "12345678900", 1L);

        when(atualizarUsuarioUseCase.executar(any(AtualizarUsuarioInput.class)))
                .thenThrow(new IllegalArgumentException("Email 'joao@email.com' ja cadastrado"));

        mockMvc.perform(put("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Email 'joao@email.com' ja cadastrado"));
    }

    @Test
    void deveRetornarBadRequestQuandoCpfJaExistirAoAtualizar() throws Exception {
        UsuarioRequest request = new UsuarioRequest("Joao", "joao@email.com", "41999999999", "12345678900", 1L);

        when(atualizarUsuarioUseCase.executar(any(AtualizarUsuarioInput.class)))
                .thenThrow(new IllegalArgumentException("CPF '12345678900' ja cadastrado"));

        mockMvc.perform(put("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("CPF '12345678900' ja cadastrado"));
    }

    @Test
    void deveRetornarBadRequestQuandoTelefoneJaExistirAoAtualizar() throws Exception {
        UsuarioRequest request = new UsuarioRequest("Joao", "joao@email.com", "41999999999", "12345678900", 1L);

        when(atualizarUsuarioUseCase.executar(any(AtualizarUsuarioInput.class)))
                .thenThrow(new IllegalArgumentException("Telefone '41999999999' ja cadastrado"));

        mockMvc.perform(put("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Telefone '41999999999' ja cadastrado"));
    }

    @Test
    void deveBuscarUsuarioPorId() throws Exception {
        UsuarioOutput output = new UsuarioOutput(1L, "Joao", "joao@email.com", "41999999999", "12345678900", "ADMIN");

        when(buscarUsuarioPorIdUseCase.executar(any(BuscarUsuarioInput.class))).thenReturn(output);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void deveListarUsuarios() throws Exception {
        UsuarioOutput output1 = new UsuarioOutput(1L, "Joao", "joao@email.com", "41999999999", "12345678900", "ADMIN");
        UsuarioOutput output2 = new UsuarioOutput(2L, "Maria", "maria@email.com", "41888888888", "98765432100", "CLIENTE");

        when(listarUsuariosUseCase.executar()).thenReturn(List.of(output1, output2));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value("Joao"))
                .andExpect(jsonPath("$[1].nome").value("Maria"));
    }

    @Test
    void deveDeletarUsuario() throws Exception {
        doNothing().when(deletarUsuarioUseCase).executar(any(DeletarUsuarioInput.class));

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isNoContent());
    }
}
