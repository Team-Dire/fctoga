package models;

public class Diretor extends Usuario {
    public Diretor(String CPF, String nomeCompleto, String senha) {
        super(CPF, nomeCompleto, senha, "Diretor");
    }
}
