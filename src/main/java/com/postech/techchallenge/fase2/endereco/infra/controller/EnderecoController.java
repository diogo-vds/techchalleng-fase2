package com.postech.techchallenge.fase2.endereco.infra.controller;

import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoInput;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoOutput;
import com.postech.techchallenge.fase2.endereco.core.usecase.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final CriarEnderecoUseCase criarEnderecoUseCase;
    private final AtualizarEnderecoUseCase atualizarEnderecoUseCase;
    private final DeletarEnderecoUseCase deletarEnderecoUseCase;
    private final BuscarEnderecoPorIdUseCase buscarEnderecoPorIdUseCase;
    private final ListarEnderecosUseCase listarEnderecosUseCase;

    public EnderecoController(
            CriarEnderecoUseCase criarEnderecoUseCase,
            AtualizarEnderecoUseCase atualizarEnderecoUseCase,
            DeletarEnderecoUseCase deletarEnderecoUseCase,
            BuscarEnderecoPorIdUseCase buscarEnderecoPorIdUseCase,
            ListarEnderecosUseCase listarEnderecosUseCase) {

        this.criarEnderecoUseCase = criarEnderecoUseCase;
        this.atualizarEnderecoUseCase = atualizarEnderecoUseCase;
        this.deletarEnderecoUseCase = deletarEnderecoUseCase;
        this.buscarEnderecoPorIdUseCase = buscarEnderecoPorIdUseCase;
        this.listarEnderecosUseCase = listarEnderecosUseCase;
    }

    @PostMapping
    public ResponseEntity<EnderecoOutput> criar(@RequestBody EnderecoInput input) {
        EnderecoOutput output = criarEnderecoUseCase.executar(input);
        return ResponseEntity.ok(output);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoOutput> atualizar(
            @PathVariable Long id,
            @RequestBody EnderecoInput input) {

        EnderecoInput inputComId = new EnderecoInput(
                id,
                input.rua(),
                input.numero(),
                input.complemento(),
                input.bairro(),
                input.cidade(),
                input.uf(),
                input.cep()
        );

        EnderecoOutput output = atualizarEnderecoUseCase.executar(inputComId);
        return ResponseEntity.ok(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoOutput> buscarPorId(@PathVariable Long id) {
        EnderecoOutput output = buscarEnderecoPorIdUseCase.executar(id);
        return ResponseEntity.ok(output);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoOutput>> listar() {
        return ResponseEntity.ok(listarEnderecosUseCase.executar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarEnderecoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}