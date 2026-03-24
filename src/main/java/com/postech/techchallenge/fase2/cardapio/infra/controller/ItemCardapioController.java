package com.postech.techchallenge.fase2.cardapio.infra.controller;

import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioInput;
import com.postech.techchallenge.fase2.cardapio.core.dto.ItemCardapioOutput;
import com.postech.techchallenge.fase2.cardapio.core.usecase.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-cardapio")
public class ItemCardapioController {

    private final CriarItemCardapioUseCase criarItemCardapioUseCase;
    private final AtualizarItemCardapioUseCase atualizarItemCardapioUseCase;
    private final DeletarItemCardapioUseCase deletarItemCardapioUseCase;
    private final BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase;
    private final ListarItemCardapioUseCase listarItemCardapioUseCase;

    public ItemCardapioController(
            CriarItemCardapioUseCase criarItemCardapioUseCase,
            AtualizarItemCardapioUseCase atualizarItemCardapioUseCase,
            DeletarItemCardapioUseCase deletarItemCardapioUseCase,
            BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase,
            ListarItemCardapioUseCase listarItemCardapioUseCase) {

        this.criarItemCardapioUseCase = criarItemCardapioUseCase;
        this.atualizarItemCardapioUseCase = atualizarItemCardapioUseCase;
        this.deletarItemCardapioUseCase = deletarItemCardapioUseCase;
        this.buscarItemCardapioPorIdUseCase = buscarItemCardapioPorIdUseCase;
        this.listarItemCardapioUseCase = listarItemCardapioUseCase;
    }

    @PostMapping
    public ResponseEntity<ItemCardapioOutput> criar(@RequestBody ItemCardapioInput input) {
        ItemCardapioOutput output = criarItemCardapioUseCase.executar(input);
        return ResponseEntity.ok(output);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapioOutput> atualizar(
            @PathVariable Long id,
            @RequestBody ItemCardapioInput input) {

        ItemCardapioInput inputComId = new ItemCardapioInput(
                id,
                input.cardapioId(),
                input.nome(),
                input.descricao(),
                input.preco(),
                input.disponivelApenasRestaurante(),
                input.caminhoFoto()
        );

        ItemCardapioOutput output = atualizarItemCardapioUseCase.executar(inputComId);
        return ResponseEntity.ok(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapioOutput> buscarPorId(@PathVariable Long id) {
        ItemCardapioOutput output = buscarItemCardapioPorIdUseCase.executar(id);
        return ResponseEntity.ok(output);
    }

    @GetMapping
    public ResponseEntity<List<ItemCardapioOutput>> listar() {
        return ResponseEntity.ok(listarItemCardapioUseCase.executar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarItemCardapioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
