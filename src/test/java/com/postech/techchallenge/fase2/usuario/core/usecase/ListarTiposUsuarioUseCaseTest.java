package com.postech.techchallenge.fase2.usuario.core.usecase;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;
import com.postech.techchallenge.fase2.usuario.core.dto.TipoUsuarioOutput;
import com.postech.techchallenge.fase2.usuario.core.gateway.TipoUsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarTiposUsuarioUseCaseTest {

    @Mock
    private TipoUsuarioGateway tipoUsuarioGateway;

    @InjectMocks
    private ListarTiposUsuarioUseCase useCase;

    @Test
    void deveListarTiposUsuario() {
        when(tipoUsuarioGateway.listarTodos())
                .thenReturn(List.of(
                        new TipoUsuario(1L, "CLIENTE"),
                        new TipoUsuario(2L, "DONO_RESTAURANTE")
                ));

        List<TipoUsuarioOutput> output = useCase.executar();

        assertEquals(2, output.size());
        assertEquals("CLIENTE", output.get(0).descricao());
        assertEquals("DONO_RESTAURANTE", output.get(1).descricao());
    }
}
