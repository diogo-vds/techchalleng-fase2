package com.postech.techchallenge.fase2.usuario.infra.controller;

import com.postech.techchallenge.fase2.usuario.core.dto.*;
import com.postech.techchallenge.fase2.usuario.core.usecase.*;
import com.postech.techchallenge.fase2.usuario.infra.controller.dto.UsuarioResponse;
import com.postech.techchallenge.fase2.usuario.infra.controller.dto.UsuarioRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    private final ListarUsuariosUseCase listarUsuariosUseCase;
    private final DeletarUsuarioUseCase deletarUsuarioUseCase;

    public UsuarioController(
            CriarUsuarioUseCase criarUsuarioUseCase,
            AtualizarUsuarioUseCase atualizarUsuarioUseCase,
            BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase,
            ListarUsuariosUseCase listarUsuariosUseCase,
            DeletarUsuarioUseCase deletarUsuarioUseCase
    ) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
        this.deletarUsuarioUseCase = deletarUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@RequestBody UsuarioRequest request) {

        var output = criarUsuarioUseCase.executar(
                new CriarUsuarioInput(
                        request.nome(),
                        request.email(),
                        request.telefone(),
                        request.cpf(),
                        request.tipoUsuarioId()
                )
        );

        return ResponseEntity.ok(toResponse(output));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(
            @PathVariable Long id,
            @RequestBody UsuarioRequest request) {

        var output = atualizarUsuarioUseCase.executar(
                new AtualizarUsuarioInput(
                        id,
                        request.nome(),
                        request.email(),
                        request.telefone(),
                        request.cpf(),
                        request.tipoUsuarioId()
                )
        );

        return ResponseEntity.ok(toResponse(output));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {

        var output = buscarUsuarioPorIdUseCase.executar(
                new BuscarUsuarioInput(id)
        );

        return ResponseEntity.ok(toResponse(output));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {

        var lista = listarUsuariosUseCase.executar();

        return ResponseEntity.ok(
                lista.stream()
                        .map(this::toResponse)
                        .toList()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        deletarUsuarioUseCase.executar(
                new DeletarUsuarioInput(id)
        );

        return ResponseEntity.noContent().build();
    }

    private UsuarioResponse toResponse(UsuarioOutput output) {
        return new UsuarioResponse(
                output.id(),
                output.nome(),
                output.email(),
                output.telefone(),
                output.cpf(),
                output.tipoUsuario()
        );
    }
}
