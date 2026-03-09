package com.postech.techchallenge.fase2.endereco.core.usecase;

import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoInput;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoOutput;
import com.postech.techchallenge.fase2.endereco.core.gateway.EnderecoGateway;

public class CriarEnderecoUseCase {

    private final EnderecoGateway enderecoGateway;

    public CriarEnderecoUseCase(EnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    public EnderecoOutput executar(EnderecoInput input) {

        if (input == null) {
            throw new IllegalArgumentException("Input não pode ser nulo");
        }

        if (input.id() != null) {
            throw new IllegalArgumentException("Id não deve ser informado para criação");
        }

        Endereco endereco = Endereco.criar(
                input.rua(),
                input.numero(),
                input.complemento(),
                input.bairro(),
                input.cidade(),
                input.uf(),
                input.cep()
        );

        Endereco salvo = enderecoGateway.salvar(endereco);

        return new EnderecoOutput(
                salvo.getId(),
                salvo.getRua(),
                salvo.getNumero(),
                salvo.getComplemento(),
                salvo.getBairro(),
                salvo.getCidade(),
                salvo.getUf(),
                salvo.getCep()
        );
    }
}
