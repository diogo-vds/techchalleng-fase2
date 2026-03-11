package com.postech.techchallenge.fase2.restaurante.infra.controller;

import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;
import com.postech.techchallenge.fase2.restaurante.core.usecase.*;
import com.postech.techchallenge.fase2.restaurante.infra.controller.dto.RestauranteRequestDTO;
import com.postech.techchallenge.fase2.restaurante.infra.controller.dto.RestauranteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    private final CriarRestauranteUseCase criarRestauranteUseCase;
    private final RecuperarRestauranteUseCase recuperarRestauranteUseCase;
    private final AtualizarRestauranteUseCase atualizarRestauranteUseCase;
    private final DeletarRestauranteUseCase deletarRestauranteUseCase;

    @Autowired
    public RestauranteController(RestauranteGateway restauranteGateway) {
        this.criarRestauranteUseCase = new CriarRestauranteUseCase(restauranteGateway);
        this.recuperarRestauranteUseCase = new RecuperarRestauranteUseCase(restauranteGateway);
        this.atualizarRestauranteUseCase = new AtualizarRestauranteUseCase(restauranteGateway);
        this.deletarRestauranteUseCase = new DeletarRestauranteUseCase(restauranteGateway);
    }

    @PostMapping
    public ResponseEntity<RestauranteResponseDTO> criar(@RequestBody RestauranteRequestDTO request) {
        try {
            Restaurante restaurante = criarRestauranteUseCase.executar(
                    request.getNome(),
                    request.getEndereco(),
                    request.getTipoCozinha(),
                    request.getHorarioFuncionamento(),
                    request.getDonoId()
            );

            RestauranteResponseDTO response = new RestauranteResponseDTO(restaurante);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponseDTO> buscarPorId(@PathVariable UUID id) {
        try {
            Restaurante restaurante = recuperarRestauranteUseCase.porId(id);
            RestauranteResponseDTO response = new RestauranteResponseDTO(restaurante);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("não encontrado")) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<RestauranteResponseDTO>> listarTodos() {
        try {
            List<Restaurante> restaurantes = recuperarRestauranteUseCase.todos();

            List<RestauranteResponseDTO> response = restaurantes.stream()
                    .map(RestauranteResponseDTO::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponseDTO> atualizar(
            @PathVariable UUID id,
            @RequestBody RestauranteRequestDTO request) {
        try {
            Restaurante restaurante = atualizarRestauranteUseCase.executar(
                    id,
                    request.getNome(),
                    request.getEndereco(),
                    request.getTipoCozinha(),
                    request.getHorarioFuncionamento()
            );

            RestauranteResponseDTO response = new RestauranteResponseDTO(restaurante);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("não encontrado")) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        try {
            deletarRestauranteUseCase.executar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("não encontrado")) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}