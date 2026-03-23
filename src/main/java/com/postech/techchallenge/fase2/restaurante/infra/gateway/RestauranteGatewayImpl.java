package com.postech.techchallenge.fase2.restaurante.infra.gateway;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;
import com.postech.techchallenge.fase2.cardapio.infra.gateway.CardapioGatewayImpl;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.entity.CardapioEntity;
import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.endereco.infra.persistence.entity.EnderecoEntity;
import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import com.postech.techchallenge.fase2.restaurante.core.gateway.RestauranteGateway;
import com.postech.techchallenge.fase2.restaurante.infra.persistence.entity.RestauranteEntity;
import com.postech.techchallenge.fase2.restaurante.infra.persistence.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RestauranteGatewayImpl implements RestauranteGateway {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        RestauranteEntity entity = new RestauranteEntity();
        entity.setNome(restaurante.getNome());
        entity.setEndereco(toEntity(restaurante.getEndereco()));
        entity.setTipoCozinha(restaurante.getTipoCozinha());
        entity.setCardapio(toEntity(restaurante.getCardapio()));
        entity.setHorarioFuncionamento(restaurante.getHorarioFuncionamento());
        entity.setDonoId(restaurante.getDonoId());
        restauranteRepository.save(entity);
        return restaurante;
    }

    @Override
    public Optional<Restaurante> buscarPorId(Long id) {
        return Optional.of(toDomain(restauranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante com ID " + id + " não encontrado"))));
    }

    @Override
    public List<Restaurante> listarTodos() {
        return restauranteRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deletar(Long id) {
        restauranteRepository.deleteById(id);
    }

    public Restaurante toDomain(RestauranteEntity entity){

        return new Restaurante(
                entity.getId(),
                entity.getNome(),
                toDomain(entity.getEndereco()),
                entity.getTipoCozinha(),
                toDomain(entity.getCardapio()),
                entity.getHorarioFuncionamento(),
                entity.getDonoId()
        );
    }

    public CardapioEntity toEntity(Cardapio cardapio) {
        CardapioEntity entity = new CardapioEntity();
        if (cardapio.getId() != null) {
            entity.setId(cardapio.getId());
        }
        entity.setNome(cardapio.getNome());
        entity.setDescricao(cardapio.getDescricao());
        entity.setPreco(cardapio.getPreco());
        entity.setDisponivelApenasRestaurante(cardapio.getDisponivelApenasRestaurante());
        entity.setCaminhoFoto(cardapio.getCaminhoFoto());
        return entity;
    }

    public Cardapio toDomain(CardapioEntity entity) {
        return Cardapio.reconstruir(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getDisponivelApenasRestaurante(),
                entity.getCaminhoFoto()
        );
    }

    private EnderecoEntity toEntity(Endereco endereco) {
        EnderecoEntity entity = new EnderecoEntity();

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

    @Override
    public boolean existePorNomeEEndereco(String nome, Endereco endereco) {
        return restauranteRepository.existsByNomeAndEnderecoRuaAndEnderecoNumeroAndEnderecoCidadeAndEnderecoUf(
                nome,
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getUf()
        );
    }


}