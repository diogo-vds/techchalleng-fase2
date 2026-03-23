package com.postech.techchallenge.fase2.usuario.infra.controller;

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
import com.postech.techchallenge.fase2.usuario.infra.controller.dto.TipoUsuarioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipos-usuario")
public class TipoUsuarioController {

    private final CriarTipoUsuarioUseCase criarTipoUsuarioUseCase;
    private final AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase;
    private final BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase;
    private final ListarTiposUsuarioUseCase listarTiposUsuarioUseCase;
    private final DeletarTipoUsuarioUseCase deletarTipoUsuarioUseCase;

    public TipoUsuarioController(
            CriarTipoUsuarioUseCase criarTipoUsuarioUseCase,
            AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase,
            BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase,
            ListarTiposUsuarioUseCase listarTiposUsuarioUseCase,
            DeletarTipoUsuarioUseCase deletarTipoUsuarioUseCase) {
        this.criarTipoUsuarioUseCase = criarTipoUsuarioUseCase;
        this.atualizarTipoUsuarioUseCase = atualizarTipoUsuarioUseCase;
        this.buscarTipoUsuarioPorIdUseCase = buscarTipoUsuarioPorIdUseCase;
        this.listarTiposUsuarioUseCase = listarTiposUsuarioUseCase;
        this.deletarTipoUsuarioUseCase = deletarTipoUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<TipoUsuarioResponse> criar(@RequestBody TipoUsuarioRequest request) {
        var output = criarTipoUsuarioUseCase.executar(
                new CriarTipoUsuarioInput(request.descricao())
        );
        return ResponseEntity.ok(toResponse(output));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuarioResponse> atualizar(
            @PathVariable Long id,
            @RequestBody TipoUsuarioRequest request) {
        var output = atualizarTipoUsuarioUseCase.executar(
                new AtualizarTipoUsuarioInput(id, request.descricao())
        );
        return ResponseEntity.ok(toResponse(output));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioResponse> buscarPorId(@PathVariable Long id) {
        var output = buscarTipoUsuarioPorIdUseCase.executar(id);
        return ResponseEntity.ok(toResponse(output));
    }

    @GetMapping
    public ResponseEntity<List<TipoUsuarioResponse>> listar() {
        return ResponseEntity.ok(
                listarTiposUsuarioUseCase.executar()
                        .stream()
                        .map(this::toResponse)
                        .toList()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarTipoUsuarioUseCase.executar(new DeletarTipoUsuarioInput(id));
        return ResponseEntity.noContent().build();
    }

    private TipoUsuarioResponse toResponse(TipoUsuarioOutput output) {
        return new TipoUsuarioResponse(
                output.id(),
                output.descricao()
        );
    }
}
