package view;

import controllers.FCToga;
import view.utils.CPFInputVerifier;

import javax.swing.*;

public class LoginGUI {
    public static JFrame render() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 400);

        // Label e TextField para cada um dos atributos:
        // CPF, Senha
        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField(14);
        cpfField.setInputVerifier(new CPFInputVerifier());
        Box cpfBox = Box.createHorizontalBox();
        cpfBox.add(cpfLabel); cpfBox.add(cpfField);

        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaField = new JPasswordField();
        Box senhaBox = Box.createHorizontalBox();
        senhaBox.add(senhaLabel); senhaBox.add(senhaField);

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
        Box loginButtonBox = Box.createHorizontalBox(); loginButtonBox.add(loginButton);

        Box frameBox = Box.createVerticalBox();
        frameBox.add(Box.createVerticalStrut(10));
        frameBox.add(cpfBox);
        frameBox.add(Box.createVerticalStrut(10));
        frameBox.add(senhaBox);
        frameBox.add(Box.createVerticalStrut(10));
        frameBox.add(loginButtonBox);
        frameBox.add(Box.createVerticalStrut(10));

        // Layout é Box Layout com eixo vertical
        frame.getContentPane().add(frameBox);
        return frame;
    }
}
