package com.postech.techchallenge.fase2.cardapio.infra.gateway;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.core.domain.ItemCardapio;
import com.postech.techchallenge.fase2.cardapio.core.gateway.CardapioGateway;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.entity.CardapioEntity;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.entity.ItemCardapioEntity;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.repository.CardapioRepository;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.repository.ItemCardapioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CardapioGatewayImpl implements CardapioGateway {

    private final CardapioRepository cardapioRepository;
    private final ItemCardapioRepository itemCardapioRepository;

    public CardapioGatewayImpl(CardapioRepository cardapioRepository, ItemCardapioRepository itemCardapioRepository) {
        this.cardapioRepository = cardapioRepository;
        this.itemCardapioRepository = itemCardapioRepository;
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

    public CardapioEntity toEntity(Cardapio cardapio) {
        CardapioEntity entity = new CardapioEntity();
        if (cardapio.getId() != null) {
            entity.setId(cardapio.getId());
        }
        entity.setNome(cardapio.getNome());
        entity.setDescricao(cardapio.getDescricao());
        return entity;
    }

    public Cardapio toDomain(CardapioEntity entity) {
        List<ItemCardapioEntity> itens = itemCardapioRepository.findByCardapioId(entity.getId());
        return Cardapio.reconstruir(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                itens.stream().map(item -> ItemCardapio.reconstruir(
                        item.getId(),
                        item.getCardapio().getId(),
                        item.getNome(),
                        item.getDescricao(),
                        item.getPreco(),
                        item.getDisponivelApenasRestaurante(),
                        item.getCaminhoFoto()
                )).toList()
        );
    }
}
