package com.postech.techchallenge.fase2.endereco.core.usecase;

import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoOutput;
import com.postech.techchallenge.fase2.endereco.core.gateway.EnderecoGateway;

import java.util.List;
import java.util.stream.Collectors;

public class ListarEnderecosUseCase {

    private final EnderecoGateway enderecoGateway;

    public ListarEnderecosUseCase(EnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    public List<EnderecoOutput> executar() {

        List<Endereco> enderecos = enderecoGateway.listarTodos();

        return enderecos.stream()
                .map(endereco -> new EnderecoOutput(
                        endereco.getId(),
                        endereco.getRua(),
                        endereco.getNumero(),
                        endereco.getComplemento(),
                        endereco.getBairro(),
                        endereco.getCidade(),
                        endereco.getUf(),
                        endereco.getCep()
                ))
                .collect(Collectors.toList());
    }
}