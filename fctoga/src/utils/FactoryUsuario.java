package utils;

import models.*;

public class FactoryUsuario {
    // A Factory Class to create an Usuario object.
    // Administrador, Advogado, Diretor, Juiz ou Promotor
    // Usa o construtor de cada uma dessas classes, ignorando parâmetros não presentes.
    public static Usuario createUsuario(String tipo, String CPF, String nome, String senha, String comarca, String numeroOAB, String estadoOAB) {
        Usuario instanciaUsuario = null;
        switch (tipo) {
            case "Administrador" -> instanciaUsuario = new Administrador(CPF, nome, senha);
            case "Promotor" -> instanciaUsuario = new Promotor(CPF, nome, senha);
            case "Juiz" -> instanciaUsuario = new Juiz(CPF, nome, senha, comarca);
            case "Diretor" -> instanciaUsuario = new Diretor(CPF, nome, senha);
            case "Advogado" -> instanciaUsuario = new Advogado(CPF, nome, senha, numeroOAB, estadoOAB);
        }
        return instanciaUsuario;
    }

}
