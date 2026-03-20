package com.postech.techchallenge.fase2.cardapio.infra.gateway;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.entity.CardapioEntity;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.repository.CardapioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CardapioGatewayImpl implements CardapioGateway {

    private final CardapioRepository cardapioRepository;

    public CardapioGatewayImpl(CardapioRepository cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    @Override
    public Cardapio salvar(Cardapio cardapio) {
        CardapioEntity entity = toEntity(cardapio);
        CardapioEntity salvo = cardapioRepository.save(entity);
        return toDomain(salvo);
    }

    @Override
    public Optional<Cardapio> buscarPorId(Long id) {
        return cardapioRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Cardapio> listarTodos() {
        return cardapioRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deletar(Long id) {
        cardapioRepository.deleteById(id);
    }

    private CardapioEntity toEntity(Cardapio cardapio) {
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

    private Cardapio toDomain(CardapioEntity entity) {
        return Cardapio.reconstruir(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getDisponivelApenasRestaurante(),
                entity.getCaminhoFoto()
        );
    }
}
