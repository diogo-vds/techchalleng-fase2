package com.postech.techchallenge.fase2.endereco.infra.gateway;

import com.postech.techchallenge.fase2.endereco.core.domain.Endereco;
import com.postech.techchallenge.fase2.endereco.infra.gateway.EnderecoGatewayImpl;
import com.postech.techchallenge.fase2.endereco.infra.persistence.entity.EnderecoEntity;
import com.postech.techchallenge.fase2.endereco.infra.persistence.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnderecoGatewayImplTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoGatewayImpl enderecoGateway;

    private Endereco endereco;
    private EnderecoEntity enderecoEntity;

    @BeforeEach
    void setup() {

        endereco = Endereco.reconstruir(
                1L,
                "Rua A",
                "123",
                "Apto 10",
                "Centro",
                "Curitiba",
                "PR",
                "80000-000"
        );

        enderecoEntity = new EnderecoEntity();
        enderecoEntity.setId(1L);
        enderecoEntity.setRua("Rua A");
        enderecoEntity.setNumero("123");
        enderecoEntity.setComplemento("Apto 10");
        enderecoEntity.setBairro("Centro");
        enderecoEntity.setCidade("Curitiba");
        enderecoEntity.setUf("PR");
        enderecoEntity.setCep("80000-000");
    }

    @Test
    void deveSalvarEndereco() {

        when(enderecoRepository.save(any(EnderecoEntity.class)))
                .thenReturn(enderecoEntity);

        Endereco resultado = enderecoGateway.salvar(endereco);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Rua A", resultado.getRua());

        verify(enderecoRepository, times(1))
                .save(any(EnderecoEntity.class));
    }

    @Test
    void deveBuscarEnderecoPorId() {

        when(enderecoRepository.findById(1L))
                .thenReturn(Optional.of(enderecoEntity));

        Optional<Endereco> resultado = enderecoGateway.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        assertEquals("Curitiba", resultado.get().getCidade());

        verify(enderecoRepository, times(1))
                .findById(1L);
    }

    @Test
    void deveRetornarVazioQuandoEnderecoNaoExiste() {

        when(enderecoRepository.findById(1L))
                .thenReturn(Optional.empty());

        Optional<Endereco> resultado = enderecoGateway.buscarPorId(1L);

        assertTrue(resultado.isEmpty());

        verify(enderecoRepository, times(1))
                .findById(1L);
    }

    @Test
    void deveListarTodosEnderecos() {

        when(enderecoRepository.findAll())
                .thenReturn(List.of(enderecoEntity));

        List<Endereco> resultado = enderecoGateway.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Rua A", resultado.get(0).getRua());

        verify(enderecoRepository, times(1))
                .findAll();
    }

    @Test
    void deveDeletarEndereco() {

        doNothing().when(enderecoRepository)
                .deleteById(1L);

        enderecoGateway.deletar(1L);

        verify(enderecoRepository, times(1))
                .deleteById(1L);
    }

    @Test
    void deveSalvarEnderecoComId() {

        Endereco endereco = Endereco.reconstruir(
                1L,"Rua","10",null,"Centro","Curitiba","PR","80000000");

        EnderecoEntity entitySalva = new EnderecoEntity();
        entitySalva.setId(1L);
        entitySalva.setRua("Rua");
        entitySalva.setNumero("10");
        entitySalva.setBairro("Centro");
        entitySalva.setCidade("Curitiba");
        entitySalva.setUf("PR");
        entitySalva.setCep("80000000");

        when(enderecoRepository.save(any())).thenReturn(entitySalva);

        Endereco resultado = enderecoGateway.salvar(endereco);

        assertEquals(1L, resultado.getId());

        verify(enderecoRepository).save(any());
    }

    @Test
    void deveSalvarEnderecoNovoSemId() {

        Endereco endereco = Endereco.criar(
                "Rua","10",null,"Centro","Curitiba","PR","80000000");

        EnderecoEntity entitySalva = new EnderecoEntity();
        entitySalva.setId(1L);
        entitySalva.setRua("Rua");
        entitySalva.setNumero("10");
        entitySalva.setBairro("Centro");
        entitySalva.setCidade("Curitiba");
        entitySalva.setUf("PR");
        entitySalva.setCep("80000000");

        when(enderecoRepository.save(any())).thenReturn(entitySalva);

        Endereco resultado = enderecoGateway.salvar(endereco);

        assertNotNull(resultado.getId());
    }

    @Test
    void deveLancarErroQuandoEntityNaoTemId() {

        Endereco endereco = Endereco.criar(
                "Rua","10",null,"Centro","Curitiba","PR","80000000");

        EnderecoEntity entitySemId = new EnderecoEntity();
        entitySemId.setRua("Rua");
        entitySemId.setNumero("10");
        entitySemId.setBairro("Centro");
        entitySemId.setCidade("Curitiba");
        entitySemId.setUf("PR");
        entitySemId.setCep("80000000");

        when(enderecoRepository.save(any()))
                .thenReturn(entitySemId);

        assertThrows(
                IllegalArgumentException.class,
                () -> enderecoGateway.salvar(endereco)
        );
    }

}