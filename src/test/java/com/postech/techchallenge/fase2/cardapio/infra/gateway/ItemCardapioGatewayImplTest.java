package com.postech.techchallenge.fase2.cardapio.infra.gateway;

import com.postech.techchallenge.fase2.cardapio.core.domain.ItemCardapio;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.entity.CardapioEntity;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.entity.ItemCardapioEntity;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.repository.CardapioRepository;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.repository.ItemCardapioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemCardapioGatewayImplTest {

    @Mock
    private ItemCardapioRepository itemCardapioRepository;

    @Mock
    private CardapioRepository cardapioRepository;

    @InjectMocks
    private ItemCardapioGatewayImpl gateway;

    private ItemCardapio item;
    private ItemCardapioEntity entity;
    private CardapioEntity cardapioEntity;

    @BeforeEach
    void setup() {
        cardapioEntity = new CardapioEntity();
        cardapioEntity.setId(1L);
        cardapioEntity.setNome("Cardapio 1");

        item = ItemCardapio.reconstruir(
                1L,
                1L,
                "Batata",
                "Frita",
                new BigDecimal("10.00"),
                true,
                "/foto.jpg"
        );

        entity = new ItemCardapioEntity();
        entity.setId(1L);
        entity.setCardapio(cardapioEntity);
        entity.setNome("Batata");
        entity.setDescricao("Frita");
        entity.setPreco(new BigDecimal("10.00"));
        entity.setDisponivelApenasRestaurante(true);
        entity.setCaminhoFoto("/foto.jpg");
    }

    @Test
    void deveSalvarItem() {
        when(cardapioRepository.findById(1L)).thenReturn(Optional.of(cardapioEntity));
        when(itemCardapioRepository.save(any(ItemCardapioEntity.class))).thenReturn(entity);

        ItemCardapio salvo = gateway.salvar(item);

        assertNotNull(salvo);
        assertEquals(1L, salvo.getId());
        assertEquals("Batata", salvo.getNome());
        verify(cardapioRepository).findById(1L);
        verify(itemCardapioRepository).save(any(ItemCardapioEntity.class));
    }

    @Test
    void deveBuscarPorId() {
        when(itemCardapioRepository.findById(1L)).thenReturn(Optional.of(entity));

        Optional<ItemCardapio> encontrado = gateway.buscarPorId(1L);

        assertTrue(encontrado.isPresent());
        assertEquals(1L, encontrado.get().getId());
    }

    @Test
    void deveListarTodos() {
        when(itemCardapioRepository.findAll()).thenReturn(List.of(entity));

        List<ItemCardapio> lista = gateway.listarTodos();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals(1L, lista.get(0).getId());
    }

    @Test
    void deveDeletar() {
        doNothing().when(itemCardapioRepository).deleteById(1L);
        gateway.deletar(1L);
        verify(itemCardapioRepository).deleteById(1L);
    }
}
