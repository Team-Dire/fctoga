package view;

import controllers.FCToga;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Verifica se há usuários cadastrados
        if (FCToga.getInstance().getUsuarios().isEmpty()) {
            // Se não houver, abre a tela de cadastro
            JFrame frameCadastro = CadastroGUI.get();
            frameCadastro.setVisible(true);
        } else {
            // Se houver, abre a tela de login
            JFrame frame = LoginGUI.TelaLogin();
            frame.setVisible(true);
        }
    }
}
