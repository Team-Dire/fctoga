package controllers;

import models.*;

public class FCToga {
    public String criarUsuario(String CPF, String senha, String nomeCompleto, String tipo, int numeroOAB, int estadoOAB, int comarca) {
        Usuario instanciaUsuario;
        switch (tipo) {
            case "Administrador" -> instanciaUsuario = new Administrador(CPF, nomeCompleto, senha);
            case "Promotor" -> instanciaUsuario = new Promotor(CPF, nomeCompleto, senha);
            case "Juiz" -> instanciaUsuario = new Juiz(CPF, nomeCompleto, senha, Integer.toString(comarca));
            case "Diretor" -> instanciaUsuario = new Diretor(CPF, nomeCompleto, senha);
            case "Advogado" ->
                    instanciaUsuario = new Advogado(CPF, nomeCompleto, senha, Integer.toString(numeroOAB), Integer.toString(estadoOAB));
        }
        return "Sucesso";
    }

    public Usuario buscarUsuario(String CPF) {
        // TODO iterar sobre uma estrutura de dados contendo os usuários. Será serializado, guardado em um BD ou o quê?
        return new Usuario("", "", ""); // Por agora só retorna um usuário genérico
    }

    public Usuario autenticarUsuario(String CPF) {
        // TODO envolve outra iteração sobre um conjunto de dados, mas também uma forma de autenticação, há de ver como isso será feito.
        return new Usuario("", "", "");
    }
}
