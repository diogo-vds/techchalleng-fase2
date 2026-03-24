package com.postech.techchallenge.fase2.cardapio.infra.controller;

import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioInput;
import com.postech.techchallenge.fase2.cardapio.core.dto.CardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.usecase.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardapio")
public class ItemCardapioController {

    private final CriarCardapioUseCase criarCardapioUseCase;
    private final AtualizarCardapioUseCase atualizarCardapioUseCase;
    private final DeletarCardapioUseCase deletarCardapioUseCase;
    private final BuscarCardapioPorIdUseCase buscarCardapioPorIdUseCase;
    private final ListarCardapioUseCase listarCardapioUseCase;

    public ItemCardapioController(
            CriarCardapioUseCase criarCardapioUseCase,
            AtualizarCardapioUseCase atualizarCardapioUseCase,
            DeletarCardapioUseCase deletarCardapioUseCase,
            BuscarCardapioPorIdUseCase buscarCardapioPorIdUseCase,
            ListarCardapioUseCase listarCardapioUseCase) {

        this.criarCardapioUseCase = criarCardapioUseCase;
        this.atualizarCardapioUseCase = atualizarCardapioUseCase;
        this.deletarCardapioUseCase = deletarCardapioUseCase;
        this.buscarCardapioPorIdUseCase = buscarCardapioPorIdUseCase;
        this.listarCardapioUseCase = listarCardapioUseCase;
    }

    @PostMapping
    public ResponseEntity<CardapioOutput> criar(@RequestBody CardapioInput input) {
        CardapioOutput output = criarCardapioUseCase.executar(input);
        return ResponseEntity.ok(output);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardapioOutput> atualizar(
            @PathVariable Long id,
            @RequestBody CardapioInput input) {

        CardapioInput inputComId = new CardapioInput(
                id,
                input.nome(),
                input.itens()
        );

        CardapioOutput output = atualizarCardapioUseCase.executar(inputComId);
        return ResponseEntity.ok(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardapioOutput> buscarPorId(@PathVariable Long id) {
        CardapioOutput output = buscarCardapioPorIdUseCase.executar(id);
        return ResponseEntity.ok(output);
    }

    @GetMapping
    public ResponseEntity<List<CardapioOutput>> listar() {
        return ResponseEntity.ok(listarCardapioUseCase.executar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarCardapioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
