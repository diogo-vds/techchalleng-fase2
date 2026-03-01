package com.postech.techchallenge.fase2.usuario.core.domain;

public class TipoUsuario {

    private Long id;
    private String descricao;

    public TipoUsuario(Long id, String nome) {
        validar(nome);
        this.id = id;
        this.descricao = nome;
    }

    private void validar(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do tipo é obrigatório");
        }
    }

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
}
