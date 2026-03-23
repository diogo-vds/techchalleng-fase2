package com.postech.techchallenge.fase2.restaurante.infra.gateway;

import com.postech.techchallenge.fase2.cardapio.core.domain.Cardapio;
import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.restaurante.core.domain.Restaurante;
import com.postech.techchallenge.fase2.restaurante.infra.persistence.entity.RestauranteEntity;
import com.postech.techchallenge.fase2.restaurante.infra.persistence.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestauranteGatewayImplTest {

    private RestauranteRepository repository;
    private RestauranteGatewayImpl gateway;

    @BeforeEach
    void setup() throws Exception {
        repository = mock(RestauranteRepository.class);
        gateway = new RestauranteGatewayImpl();

        Field field = RestauranteGatewayImpl.class
                .getDeclaredField("restauranteRepository");
        field.setAccessible(true);
        field.set(gateway, repository);
    }

    private Endereco criarEndereco() {
        return Endereco.criar(
                "Rua A", "123", null,
                "Centro", "São Paulo", "SP", "12345678"
        );
    }

    private Cardapio criarCardapio() {
        return Cardapio.criar(
                "Pizza",
                "Descrição",
                new BigDecimal("50.00"),
                true,
                "/img.png"
        );
    }

    private RestauranteEntity criarEntityCompleta(Long id) {
        var endereco = new com.postech.techchallenge.fase2.endereco.infra.persistence.entity.EnderecoEntity();
        endereco.setId(1L);
        endereco.setRua("Rua A");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("São Paulo");
        endereco.setUf("SP");
        endereco.setCep("12345678");

        var cardapio = new com.postech.techchallenge.fase2.cardapio.infra.persistence.entity.CardapioEntity();
        cardapio.setId(1L);
        cardapio.setNome("Pizza");
        cardapio.setDescricao("Descrição");
        cardapio.setPreco(new BigDecimal("50.00"));
        cardapio.setDisponivelApenasRestaurante(true);
        cardapio.setCaminhoFoto("/img.png");

        RestauranteEntity entity = new RestauranteEntity();
        entity.setId(id);
        entity.setNome("Nome");
        entity.setEndereco(endereco);
        entity.setTipoCozinha("Italiana");
        entity.setCardapio(cardapio);
        entity.setHorarioFuncionamento("08:00");
        entity.setDonoId(1L);

        return entity;
    }

    @Test
    void deveSalvarRestauranteComEnderecoComId() {

        var endereco = Endereco.reconstruir(
                10L,
                "Rua A", "123", null,
                "Centro", "São Paulo", "SP", "12345678"
        );

        var restaurante = new Restaurante(
                "Nome",
                endereco,
                "Italiana",
                criarCardapio(),
                "08:00",
                1L
        );

        gateway.salvar(restaurante);

        var captor = org.mockito.ArgumentCaptor.forClass(RestauranteEntity.class);
        verify(repository).save(captor.capture());

        assertEquals(10L, captor.getValue().getEndereco().getId()); // 🔥 cobre if TRUE
    }

    @Test
    void deveBuscarPorId() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(criarEntityCompleta(1L)));

        var result = gateway.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void deveFalharAoBuscarPorId() {

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                gateway.buscarPorId(1L)
        );
    }

    @Test
    void deveListarTodos() {

        when(repository.findAll())
                .thenReturn(List.of(criarEntityCompleta(1L)));

        var lista = gateway.listarTodos();

        assertEquals(1, lista.size());
    }

    @Test
    void deveDeletar() {

        gateway.deletar(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void deveConverterCardapioComId() {

        var cardapio = criarCardapio();

        var entity = gateway.toEntity(cardapio);
        entity.setId(1L);

        var domain = gateway.toDomain(entity);

        assertEquals(entity.getNome(), domain.getNome());
    }

    @Test
    void deveConverterCardapioSemId() {

        var cardapio = criarCardapio();

        var entity = gateway.toEntity(cardapio);

        assertNull(entity.getId()); // 🔥 cobre o if
    }

    @Test
    void deveFalharAoConverterCardapioSemIdParaDomain() {

        var entity = gateway.toEntity(criarCardapio());

        assertThrows(IllegalArgumentException.class, () ->
                gateway.toDomain(entity)
        );
    }

    @Test
    void deveConverterRestauranteCompleto() {

        var entity = criarEntityCompleta(1L);

        var domain = gateway.toDomain(entity);

        assertEquals("Nome", domain.getNome());
        assertEquals("Italiana", domain.getTipoCozinha());
    }

    @Test
    void deveFalharQuandoEnderecoSemId() {

        var entity = criarEntityCompleta(1L);
        entity.getEndereco().setId(null);

        assertThrows(IllegalArgumentException.class, () ->
                gateway.toDomain(entity)
        );
    }

    @Test
    void deveConverterCardapioComIdPreservandoId() {

        var cardapio = Cardapio.reconstruir(
                99L,
                "Pizza",
                "Descricao",
                new BigDecimal("50.00"),
                true,
                "/img.png"
        );

        var entity = gateway.toEntity(cardapio);

        assertEquals(99L, entity.getId()); // 🔥 cobre if TRUE
    }

    @Test
    void deveConverterRestauranteValidandoTodosCampos() {

        var entity = criarEntityCompleta(1L);

        var domain = gateway.toDomain(entity);

        assertEquals(1L, domain.getId());
        assertEquals("Nome", domain.getNome());
        assertEquals("Italiana", domain.getTipoCozinha());
        assertEquals("08:00", domain.getHorarioFuncionamento());
        assertEquals(1L, domain.getDonoId());

        assertNotNull(domain.getEndereco());
        assertNotNull(domain.getCardapio());
    }
}