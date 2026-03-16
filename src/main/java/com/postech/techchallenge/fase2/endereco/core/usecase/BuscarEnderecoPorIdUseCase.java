package com.postech.techchallenge.fase2.endereco.core.usecase;

import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoOutput;
import com.postech.techchallenge.fase2.endereco.core.gateway.EnderecoGateway;

public class BuscarEnderecoPorIdUseCase {

    private final EnderecoGateway enderecoGateway;

    public BuscarEnderecoPorIdUseCase(EnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    public EnderecoOutput executar(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }

        Endereco endereco = enderecoGateway
                .buscarPorId(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Endereço não encontrado"));

        return new EnderecoOutput(
                endereco.getId(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getUf(),
                endereco.getCep()
        );
    }
}