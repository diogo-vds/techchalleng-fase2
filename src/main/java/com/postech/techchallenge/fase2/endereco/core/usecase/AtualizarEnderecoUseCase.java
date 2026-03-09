package com.postech.techchallenge.fase2.endereco.core.usecase;

import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoInput;
import com.postech.techchallenge.fase2.endereco.core.dto.EnderecoOutput;
import com.postech.techchallenge.fase2.endereco.core.gateway.EnderecoGateway;

public class AtualizarEnderecoUseCase {

    private final EnderecoGateway enderecoGateway;

    public AtualizarEnderecoUseCase(EnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    public EnderecoOutput executar(EnderecoInput input) {

        if (input == null) {
            throw new IllegalArgumentException("Input não pode ser nulo");
        }

        Endereco enderecoExistente = enderecoGateway
                .buscarPorId(input.id())
                .orElseThrow(() ->
                        new IllegalArgumentException("Endereço não encontrado"));

        Endereco enderecoAtualizado = Endereco.reconstruir(
                enderecoExistente.getId(),
                input.rua(),
                input.numero(),
                input.complemento(),
                input.bairro(),
                input.cidade(),
                input.uf(),
                input.cep()
        );

        Endereco salvo = enderecoGateway.salvar(enderecoAtualizado);

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
