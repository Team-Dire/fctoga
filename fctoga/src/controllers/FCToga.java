package controllers;

import models.Usuario;
import utils.Database;

import java.util.List;
import java.util.Objects;

public class FCToga {
    private final Database db = new Database();

    public String criarUsuario(String CPF, String senha, String nomeCompleto, String tipo, String numeroOAB, String estadoOAB, String comarca) {
        // Primeiro, verifica se usuario já existe.
        try {
            this.buscarUsuario(CPF);
            return "Usuário já existe.";
        }
        // Caso não exista, cria um.
        catch (RuntimeException e) {
            boolean statusCriacao = db.addUsuario(CPF, senha, nomeCompleto, tipo, numeroOAB, estadoOAB, comarca);
            return statusCriacao ? "Sucesso." : "Falha na criação de usuário.";
        }
    }

    public Usuario buscarUsuario(String CPF) {
        List<Usuario> usuarios = db.getUsuarios();
        Usuario primeiraOcorrencia = usuarios.stream().filter(usuario -> Objects.equals(usuario.getCPF(), CPF)).findFirst().orElse(null);
        if (primeiraOcorrencia != null) return primeiraOcorrencia;
        else throw new RuntimeException("Usuário com esse CPF não existe.");
    }

    public Usuario autenticarUsuario(String CPF, String senha) {
        Usuario instanciaUsuario;
        try {
            instanciaUsuario = this.buscarUsuario(CPF);
            // Permitir a autenticação apenas se a senha estiver correta.
            // Esse processo não envolve nenhuma camada de segurança.
            if (instanciaUsuario.verificarSenha(senha)) return instanciaUsuario;
            else throw new RuntimeException("Senha incorreta.");
        } catch (RuntimeException e) {
            throw new RuntimeException("Impossível autenticar usuário inexistente.");
        }
    }
}
