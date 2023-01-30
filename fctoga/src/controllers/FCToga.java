package controllers;

import models.Processo;
import models.Usuario;
import utils.FactoryUsuario;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class FCToga implements Serializable {
    private static FCToga instance;
    private final List<Usuario> usuarios = new ArrayList<>();
    private Usuario usuarioLogado;
    public final List<Processo> processos = new ArrayList<>();

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public String criarUsuario(String CPF, String senha, String nomeCompleto, String tipo, String numeroOAB, String estadoOAB, String comarca) {
        // Primeiro, verifica se usuario já existe.
        if (usuarios.stream().anyMatch(usuario -> usuario.getCPF().equals(CPF))) {
            return "Usuário já existe.";
        }
        // Caso não exista, cria um usuário.
        try {
            Usuario instanciaUsuario = FactoryUsuario.createUsuario(tipo, CPF, nomeCompleto, senha, comarca, numeroOAB, estadoOAB);
            this.usuarios.add(instanciaUsuario);
            FCToga.serializeInstance();
        } catch (Exception eCriacaoUsuario) {
            return "Erro ao criar usuário: " + eCriacaoUsuario.getMessage();
        }
        return "Usuário criado com sucesso.";
    }

    public Usuario buscarUsuario(String CPF) {
        Usuario primeiraOcorrencia = this.usuarios.stream().filter(usuario -> Objects.equals(usuario.getCPF(), CPF)).findFirst().orElse(null);
        if (primeiraOcorrencia != null) return primeiraOcorrencia;
        else throw new NoSuchElementException("Usuário com esse CPF não existe.");
    }

    public Usuario autenticarUsuario(String CPF, String senha) {
        Usuario instanciaUsuario;
        try {
            instanciaUsuario = buscarUsuario(CPF);
        } catch (NoSuchElementException eUsuarioNaoExiste) {
            throw new NoSuchElementException("Usuário com esse CPF não existe.");
        }
        if (instanciaUsuario.getSenha().equals(senha)) {
            this.usuarioLogado = instanciaUsuario;
            return instanciaUsuario;
        } else throw new IllegalArgumentException("Senha incorreta.");
    }

    public Usuario getUsuarioLogado() {
        return this.usuarioLogado;
    }

    public static FCToga getInstance() {
        if (instance == null) {
            instance = FCToga.loadSerializedInstance();
        }
        return instance;
    }

    public static void serializeInstance() {
        try {
            FileOutputStream fileOut = new FileOutputStream("fctoga.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(FCToga.instance);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static FCToga loadSerializedInstance() {
        try {
            return (FCToga) new java.io.ObjectInputStream(new java.io.FileInputStream("fctoga.ser")).readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new FCToga();
        }
    }
}
