package com.postech.techchallenge.fase2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestauranteApplicationTest {

    @Test
    void contextLoads() {
        // Teste vazio apenas para verificar se o contexto Spring sobe corretamente
    }

    @Test
    void mainMethodRuns() {
        RestauranteApplication.main(new String[]{});
    }
}