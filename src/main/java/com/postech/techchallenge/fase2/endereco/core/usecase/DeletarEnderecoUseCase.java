package com.postech.techchallenge.fase2.endereco.core.usecase;

import com.postech.techchallenge.fase2.endereco.core.gateway.EnderecoGateway;

public class DeletarEnderecoUseCase {

    private final EnderecoGateway enderecoGateway;

    public DeletarEnderecoUseCase(EnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    public void executar(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }

        boolean existe = enderecoGateway.buscarPorId(id).isPresent();

        if (!existe) {
            throw new IllegalArgumentException("Endereço não encontrado");
        }

        enderecoGateway.deletar(id);
    }
}