package view;

import controllers.FCToga;
import view.utils.CPFInputVerifier;
import view.utils.NumeroOABInputVerifier;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class CadastroGUI {
    private static final String[] TIPOS_USUARIO = {"Administrador", "Advogado", "Diretor", "Juiz", "Promotor"};
    private static final String[] ESTADOS = {
            "AC", "AL", "AP", "AM", "BA", "CE",
            "DF", "ES", "GO", "MA", "MT", "MS",
            "MG", "PA", "PB", "PR", "PE", "PI",
            "RJ", "RN", "RS", "RO", "RR", "SC",
            "SP", "SE", "TO"
    };

    public static JFrame render(boolean permitirApenasAdministrador) {
        JFrame frame = new JFrame("Cadastro");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ===== TIPO DO USUARIO ======
        JLabel tipoUsuarioLabel = new JLabel("Tipo de usuário:");
        JComboBox<String> tipoUsuario = new JComboBox<>();

        if (permitirApenasAdministrador) {
            tipoUsuario.addItem(TIPOS_USUARIO[0]);
        } else {
            Arrays.stream(TIPOS_USUARIO).forEach(tipoUsuario::addItem);
        }

        // ===== ESTADO OAB =====
        JLabel estadoOABLabel = new JLabel("Estado OAB:");
        JComboBox<String> estadoOAB = new JComboBox<>();
        // Itens são os 27 estados brasileiros (incluso DF)
        for (String estado : ESTADOS)
            estadoOAB.addItem(estado);

        // ===== CPF =====
        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField(11);
        cpfField.setInputVerifier(new CPFInputVerifier());

        // ===== NOME =====
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();


        // ===== SENHA =====
        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaField = new JPasswordField();


        // ===== COMARCA =====
        JLabel comarcaLabel = new JLabel("Comarca:");
        JTextField comarcaField = new JTextField();

        // ===== NUMERO OAB =====
        JLabel numeroOABLabel = new JLabel("Número OAB:");
        JTextField numeroOABField = new JTextField(6);
        numeroOABField.setInputVerifier(new NumeroOABInputVerifier());

        // Definindo quais campos aparecem em cada tipo do usuário
        // Atualizado sempre que opção escolhida em tipoUsuario é alterada
        tipoUsuario.addActionListener(e -> {
            String tipo = tipoUsuario.getSelectedItem().toString();
            switch (tipo) {
                case "Administrador", "Diretor" -> {
                    estadoOABLabel.setVisible(false);
                    estadoOAB.setVisible(false);
                    comarcaLabel.setVisible(false);
                    comarcaField.setVisible(false);
                    numeroOABLabel.setVisible(false);
                    numeroOABField.setVisible(false);
                }
                case "Advogado" -> {
                    estadoOABLabel.setVisible(true);
                    estadoOAB.setVisible(true);
                    comarcaLabel.setVisible(false);
                    comarcaField.setVisible(false);
                    numeroOABLabel.setVisible(true);
                    numeroOABField.setVisible(true);
                }
                case "Juiz", "Promotor" -> {
                    estadoOABLabel.setVisible(false);
                    estadoOAB.setVisible(false);
                    comarcaLabel.setVisible(true);
                    comarcaField.setVisible(true);
                    numeroOABLabel.setVisible(false);
                    numeroOABField.setVisible(false);
                }
            }
        });
        // Seleciona "Administrador" por padrão
        tipoUsuario.setSelectedIndex(0);

        // Botão que chama o método de cadastro
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, FCToga.getInstance().criarUsuario(
                    cpfField.getText(),
                    senhaField.getText(),
                    nomeField.getText(),
                    tipoUsuario.getSelectedItem().toString(),
                    comarcaField.getText(),
                    estadoOAB.getSelectedItem().toString(),
                    numeroOABField.getText()
            ));

            frame.dispose();
        });

        // Utilizando GridBagLayout
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);
        // Labels à esquerda, campos à direita
        // Adicionando na seguinte ordem: Tipo, CPF, Nome, Senha, Comarca, Número OAB e Estado OAB
        c.gridx = 0;
        c.gridy = 0;
        frame.add(tipoUsuarioLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        frame.add(tipoUsuario, c);
        c.gridx = 0;
        c.gridy = 1;
        frame.add(cpfLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        frame.add(cpfField, c);
        c.gridx = 0;
        c.gridy = 2;
        frame.add(nomeLabel, c);
        c.gridx = 1;
        c.gridy = 2;
        frame.add(nomeField, c);
        c.gridx = 0;
        c.gridy = 3;
        frame.add(senhaLabel, c);
        c.gridx = 1;
        c.gridy = 3;
        frame.add(senhaField, c);
        c.gridx = 0;
        c.gridy = 4;
        frame.add(comarcaLabel, c);
        c.gridx = 1;
        c.gridy = 4;
        frame.add(comarcaField, c);
        c.gridx = 0;
        c.gridy = 5;
        frame.add(numeroOABLabel, c);
        c.gridx = 1;
        c.gridy = 5;
        frame.add(numeroOABField, c);
        c.gridx = 0;
        c.gridy = 6;
        frame.add(estadoOABLabel, c);
        c.gridx = 1;
        c.gridy = 6;
        frame.add(estadoOAB, c);
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        frame.add(cadastrarButton, c);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        return frame;
    }
}
