package com.postech.techchallenge.fase2.usuario.infra.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_usuario_id", nullable = false)
    private TipoUsuarioEntity tipoUsuario;

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getCpf() { return cpf; }
    public TipoUsuarioEntity getTipoUsuario() { return tipoUsuario; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setTipoUsuario(TipoUsuarioEntity tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
