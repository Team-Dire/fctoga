package models;

public class Usuario {
    private String CPF;
    private String nomeCompleto;
    private String senha;

    // Construtores, getters e setters.
    // region Boilerplate
    public Usuario(String CPF, String nomeCompleto, String senha) {
        this.CPF = CPF;
        this.nomeCompleto = nomeCompleto;
        this.senha = senha;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    //endregion
}
