package com.postech.techchallenge.fase2.cardapio.infra.gateway;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.entity.CardapioEntity;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.repository.CardapioRepository;
import com.postech.techchallenge.fase2.cardapio.infra.persistence.repository.ItemCardapioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardapioGatewayImplTest {

    @Mock
    private CardapioRepository cardapioRepository;

    @Mock
    private ItemCardapioRepository itemCardapioRepository;

    @InjectMocks
    private CardapioGatewayImpl cardapioGateway;

    private Cardapio cardapio;
    private CardapioEntity cardapioEntity;

    @BeforeEach
    void setup() {
        cardapio = Cardapio.reconstruir(
                1L,
                "X-Salada",
                "Hamburguer",
                Collections.emptyList()
        );

        cardapioEntity = new CardapioEntity();
        cardapioEntity.setId(1L);
        cardapioEntity.setNome("X-Salada");
        cardapioEntity.setDescricao("Hamburguer");

    }

    @Test
    void deveSalvarCardapio() {
        when(itemCardapioRepository.findByCardapioId(anyLong())).thenReturn(Collections.emptyList());
        when(cardapioRepository.save(any(CardapioEntity.class))).thenReturn(cardapioEntity);

        Cardapio resultado = cardapioGateway.salvar(cardapio);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("X-Salada", resultado.getNome());
        verify(cardapioRepository, times(1)).save(any(CardapioEntity.class));
    }

    @Test
    void deveBuscarCardapioPorId() {
        when(itemCardapioRepository.findByCardapioId(anyLong())).thenReturn(Collections.emptyList());
        when(cardapioRepository.findById(1L)).thenReturn(Optional.of(cardapioEntity));

        Optional<Cardapio> resultado = cardapioGateway.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        assertEquals("X-Salada", resultado.get().getNome());
        verify(cardapioRepository, times(1)).findById(1L);
    }

    @Test
    void deveRetornarVazioQuandoCardapioNaoExiste() {
        when(cardapioRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Cardapio> resultado = cardapioGateway.buscarPorId(1L);

        assertTrue(resultado.isEmpty());
        verify(cardapioRepository, times(1)).findById(1L);
    }

    @Test
    void deveListarTodosCardapios() {
        when(itemCardapioRepository.findByCardapioId(anyLong())).thenReturn(Collections.emptyList());
        when(cardapioRepository.findAll()).thenReturn(List.of(cardapioEntity));

        List<Cardapio> resultado = cardapioGateway.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("X-Salada", resultado.get(0).getNome());
        verify(cardapioRepository, times(1)).findAll();
    }

    @Test
    void deveDeletarCardapio() {
        doNothing().when(cardapioRepository).deleteById(1L);

        cardapioGateway.deletar(1L);

        verify(cardapioRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveSalvarCardapioNovoSemId() {
        Cardapio novoCardapio = Cardapio.criar(
                "X-Salada",
                "Hamburguer",
                Collections.emptyList()
        );

        when(itemCardapioRepository.findByCardapioId(anyLong())).thenReturn(Collections.emptyList());
        when(cardapioRepository.save(any(CardapioEntity.class))).thenReturn(cardapioEntity);


        Cardapio resultado = cardapioGateway.salvar(novoCardapio);

        assertNotNull(resultado.getId());
        assertEquals(1L, resultado.getId());
    }

    @Test
    void deveLancarErroQuandoEntityNaoTemId() {
        CardapioEntity entitySemId = new CardapioEntity();
        entitySemId.setNome("X-Salada");
        entitySemId.setDescricao("Hamburguer");

        when(cardapioRepository.save(any(CardapioEntity.class))).thenReturn(entitySemId);

        assertThrows(
                IllegalArgumentException.class,
                () -> cardapioGateway.salvar(cardapio)
        );
    }
}
