package models;

public class Promotor extends Usuario {
    public Promotor(String CPF, String nomeCompleto, String senha) {
        super(CPF, nomeCompleto, senha, "Promotor");
    }
}
