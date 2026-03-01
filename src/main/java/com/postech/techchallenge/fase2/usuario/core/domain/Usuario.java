package com.postech.techchallenge.fase2.usuario.core.domain;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private TipoUsuario tipoUsuario;

    public Usuario(Long id,
                    String nome,
                    String email,
                    String telefone,
                    String cpf,
                    TipoUsuario tipoUsuario) {

        if (tipoUsuario == null) {
            throw new IllegalArgumentException("TipoUsuario é obrigatório");
        }

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.tipoUsuario = tipoUsuario;
    }
        public static Usuario criar(String nome,
                                String email,
                                String telefone,
                                String cpf,
                                TipoUsuario tipoUsuario) {

        return new Usuario(null, nome, email, telefone, cpf, tipoUsuario);
    }

    public static Usuario reconstruir(Long id,
                                      String nome,
                                      String email,
                                      String telefone,
                                      String cpf,
                                      TipoUsuario tipoUsuario) {

        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo na reconstrução");
        }

        return new Usuario(id, nome, email, telefone, cpf, tipoUsuario);
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getCpf() { return cpf; }
    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
}
