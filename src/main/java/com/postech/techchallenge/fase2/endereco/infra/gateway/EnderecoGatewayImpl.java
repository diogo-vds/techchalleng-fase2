package com.postech.techchallenge.fase2.endereco.infra.gateway;

import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.endereco.core.gateway.EnderecoGateway;
import com.postech.techchallenge.fase2.endereco.infra.persistence.entity.EnderecoEntity;
import com.postech.techchallenge.fase2.endereco.infra.persistence.repository.EnderecoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EnderecoGatewayImpl implements EnderecoGateway {

    private final EnderecoRepository enderecoRepository;

    public EnderecoGatewayImpl(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public Endereco salvar(Endereco endereco) {
        EnderecoEntity entity = toEntity(endereco);
        EnderecoEntity salvo = enderecoRepository.save(entity);

        return toDomain(salvo);
    }

    @Override
    public Optional<Endereco> buscarPorId(Long id) {
        return enderecoRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deletar(Long id) {
        enderecoRepository.deleteById(id);
    }

    // =========================
    // MAPPERS
    // =========================

    private EnderecoEntity toEntity(Endereco endereco) {
        EnderecoEntity entity = new EnderecoEntity();
        // Não precisa setar ID se for novo, JPA gera automaticamente
        if (endereco.getId() != null) {
            entity.setId(endereco.getId());
        }
        entity.setRua(endereco.getRua());
        entity.setNumero(endereco.getNumero());
        entity.setComplemento(endereco.getComplemento());
        entity.setBairro(endereco.getBairro());
        entity.setCidade(endereco.getCidade());
        entity.setUf(endereco.getUf());
        entity.setCep(endereco.getCep());

        return entity;
    }

    private Endereco toDomain(EnderecoEntity entity) {
        if (entity.getId() == null) {
            // Para consistência, sempre usamos reconstruir com ID
            throw new IllegalArgumentException("EnderecoEntity sem ID não pode ser reconstruído");
        }

        return Endereco.reconstruir(
                entity.getId(),
                entity.getRua(),
                entity.getNumero(),
                entity.getComplemento(),
                entity.getBairro(),
                entity.getCidade(),
                entity.getUf(),
                entity.getCep()
        );
    }
}