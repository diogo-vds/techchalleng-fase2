package com.postech.techchallenge.fase2.cardapio.infra.gateway;

import com.postech.techchallenge.fase2.cardapio.core.domain.ItemCardapio;
import com.postech.techchallenge.fase2.cardapio.core.gateway.ItemCardapioGateway;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.entity.ItemCardapioEntity;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.repository.ItemCardapioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ItemCardapioGatewayImpl implements ItemCardapioGateway {

    private final ItemCardapioRepository itemCardapioRepository;

    public ItemCardapioGatewayImpl(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    @Override
    public ItemCardapio salvar(ItemCardapio item) {
        ItemCardapioEntity entity = toEntity(item);
        ItemCardapioEntity salvo = itemCardapioRepository.save(entity);
        return toDomain(salvo);
    }

    @Override
    public Optional<ItemCardapio> buscarPorId(Long id) {
        return itemCardapioRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<ItemCardapio> listarTodos() {
        return itemCardapioRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deletar(Long id) {
        itemCardapioRepository.deleteById(id);
    }

    public ItemCardapioEntity toEntity(ItemCardapio item) {
        ItemCardapioEntity entity = new ItemCardapioEntity();
        if (item.getId() != null) {
            entity.setId(item.getId());
        }
        entity.setNome(item.getNome());
        entity.setDescricao(item.getDescricao());
        entity.setPreco(item.getPreco());
        entity.setDisponivelApenasRestaurante(item.getDisponivelApenasRestaurante());
        entity.setCaminhoFoto(item.getCaminhoFoto());
        return entity;
    }

    public ItemCardapio toDomain(ItemCardapioEntity entity) {
        return ItemCardapio.reconstruir(
                entity.getId(),
                entity.getCardapio().getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getDisponivelApenasRestaurante(),
                entity.getCaminhoFoto()
        );
    }
}
