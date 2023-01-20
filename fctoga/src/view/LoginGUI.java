package view;

import controllers.FCToga;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

public class LoginGUI {
    public static JFrame TelaLogin() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 400);

        // Label e TextField para cada um dos atributos:
        // CPF, Senha
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Long.class);
        numberFormatter.setAllowsInvalid(false);
        // Senha é um JPasswordField
        JLabel cpfLabel = new JLabel("CPF:");
        JFormattedTextField cpfField = new JFormattedTextField(numberFormatter);
        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaField = new JPasswordField();

        // Botão que chama o método de login
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            try {
                FCToga.getInstance().autenticarUsuario(cpfField.getText(), senhaField.getText());
                JOptionPane.showMessageDialog(frame, "Login realizado com sucesso!");
                frame.dispose();
                JFrame frameMenuPrincipal = MenuPrincipal.MenuPrincipal();
                frameMenuPrincipal.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao realizar login: " + ex.getMessage());
            }
        });

        frame.getContentPane().add(cpfLabel);
        frame.getContentPane().add(cpfField);
        frame.getContentPane().add(senhaLabel);
        frame.getContentPane().add(senhaField);
        frame.getContentPane().add(loginButton);

        // Layout é Box Layout com eixo vertical
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        return frame;
    }
}
