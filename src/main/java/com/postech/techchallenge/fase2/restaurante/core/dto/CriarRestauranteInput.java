package com.postech.techchallenge.fase2.restaurante.core.dto;

import com.postech.techchallenge.fase2.usuario.core.domain.TipoUsuario;

import java.time.LocalTime;

public record CriarRestauranteInput (

    String nomeRestaurante,
    String donoRestaurante,
    TipoUsuario tipoUsuario,
    String enderecoRestaurante,
    String tipoCozinha,
    LocalTime horarioAbertura,
    LocalTime horarioFechamento,
    String cardapio
){

}
