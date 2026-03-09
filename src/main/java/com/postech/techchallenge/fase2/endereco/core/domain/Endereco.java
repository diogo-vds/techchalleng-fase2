package com.postech.techchallenge.fase2.endereco.core.domain;

public class Endereco {

    private final Long id;
    private final String rua;
    private final String numero;
    private final String complemento;
    private final String bairro;
    private final String cidade;
    private final String uf;
    private final String cep;

    private Endereco(Long id, String rua, String numero, String complemento, String bairro, String cidade, String uf, String cep) {

        validarCampo(rua, "rua");
        validarCampo(numero, "número");
        validarCampo(bairro, "bairro");
        validarCampo(cidade, "cidade");
        validarCampo(uf, "UF");
        validarCampo(cep, "CEP");

        this.id = id;
        this.rua = rua.trim();
        this.numero = numero.trim();
        this.complemento = complemento == null ? null : complemento.trim();
        this.bairro = bairro.trim();
        this.cidade = cidade.trim();
        this.uf = uf.trim().toUpperCase();
        if (this.uf.length() != 2) {
            throw new IllegalArgumentException("UF inválido");
        }

        String cepNumerico = cep.replaceAll("[^0-9]", "");

        if (cepNumerico.length() != 8)
            throw new IllegalArgumentException("CEP deve conter 8 dígitos numéricos");

        this.cep = cepNumerico;
    }

    public static Endereco criar(String rua,
                                 String numero,
                                 String complemento,
                                 String bairro,
                                 String cidade,
                                 String uf,
                                 String cep) {
        return new Endereco(null, rua, numero, complemento, bairro, cidade, uf, cep);
    }

    public static Endereco reconstruir(Long id,
                                       String rua,
                                       String numero,
                                       String complemento,
                                       String bairro,
                                       String cidade,
                                       String uf,
                                       String cep) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo na reconstrução");
        }

        return new Endereco(id, rua, numero, complemento, bairro, cidade, uf, cep);
    }

    private static void validarCampo(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O campo " + campo + " é obrigatório");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco endereco)) return false;
        return id != null && id.equals(endereco.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Long getId() {
        return id;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }
}
