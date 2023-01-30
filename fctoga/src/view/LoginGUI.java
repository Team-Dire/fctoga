package view;

import controllers.FCToga;
import view.utils.CPFInputVerifier;

import javax.swing.*;
import java.awt.*;

public class LoginGUI {
    public static JFrame render() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Label e TextField para cada um dos atributos:
        // CPF, Senha
        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField(14);
        cpfField.setInputVerifier(new CPFInputVerifier());

        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaField = new JPasswordField();

        // Botão que chama o método de login
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            try {
                FCToga.getInstance().autenticarUsuario(cpfField.getText(), senhaField.getText());
                JOptionPane.showMessageDialog(frame, "Login realizado com sucesso!");
                frame.dispose();
                JFrame frameMenuPrincipal = MenuPrincipal.render();
                frameMenuPrincipal.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao realizar login: " + ex.getMessage());
            }
        });

        // Grid 3 linhas 2 colunas
        // Botão na última linha ocupando 2 colunas
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0;
        c.gridy = 0;
        frame.add(cpfLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        frame.add(cpfField, c);
        c.gridx = 0;
        c.gridy = 1;
        frame.add(senhaLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        frame.add(senhaField, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        frame.add(loginButton, c);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        return frame;
    }
}
