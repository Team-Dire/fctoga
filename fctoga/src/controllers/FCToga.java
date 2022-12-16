package controllers;

import models.Usuario;
import utils.Database;

import java.util.List;
import java.util.Objects;

public class FCToga {
    private final Database db = new Database();

    public String criarUsuario(String CPF, String senha, String nomeCompleto, String tipo, String numeroOAB, String estadoOAB, String comarca) {
        boolean statusCriacao = db.addUsuario(CPF, senha, nomeCompleto, tipo, numeroOAB, estadoOAB, comarca);
        return statusCriacao ? "Sucesso." : "Falha na criação de usuário.";
    }

    public Usuario buscarUsuario(String CPF) {
        List<Usuario> usuarios = db.getUsuarios();
        Usuario primeiraOcorrencia = usuarios
                .stream()
                .filter(usuario -> Objects.equals(usuario.getCPF(), CPF))
                .findFirst()
                .orElse(null);
        if (primeiraOcorrencia != null)
            return primeiraOcorrencia;
        else
            throw new RuntimeException("Usuário com esse CPF não existe.");
    }

    public Usuario autenticarUsuario(String CPF) {
        // TODO como esse método não apresenta 'Senha' como entrada, interpretei-o como a mesma função do método "buscarUsuario"
        return buscarUsuario(CPF);
    }
}
