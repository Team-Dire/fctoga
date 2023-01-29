package models;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String CPF;
    private String nomeCompleto;
    private String senha;
    private final String tipoUsuario;

    // Construtores, getters e setters.
    // region Boilerplate
    public Usuario(String CPF, String nomeCompleto, String senha, String tipoUsuario) {
        this.CPF = CPF;
        this.nomeCompleto = nomeCompleto;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }
    //endregion

    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }
}
