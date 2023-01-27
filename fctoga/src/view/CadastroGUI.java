package view;

import controllers.FCToga;
import view.utils.CPFInputVerifier;
import view.utils.NumeroOABInputVerifier;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class CadastroGUI {
    private static final String[] TIPOS = {"Administrador", "Advogado", "Diretor", "Juiz", "Promotor"};
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
        frame.setSize(300, 600);

        // ===== TIPO DO USUARIO ======
        JLabel tipoUsuarioLabel = new JLabel("Tipo de usuário:");
        JComboBox<String> tipoUsuario = new JComboBox<>();

        if (permitirApenasAdministrador) {
            tipoUsuario.addItem(TIPOS[0]);
        } else {
            Arrays.stream(TIPOS).forEach(tipoUsuario::addItem);
        }

        Box tipoUsuarioBox = Box.createHorizontalBox();
        tipoUsuarioBox.add(tipoUsuarioLabel);
        tipoUsuarioBox.add(tipoUsuario);

        // ===== ESTADO OAB =====
        JLabel estadoOABLabel = new JLabel("Estado OAB:");
        JComboBox<String> estadoOAB = new JComboBox<>();
        // Itens são os 27 estados brasileiros (incluso DF)
        for (String estado : ESTADOS)
            estadoOAB.addItem(estado);
        Box estadoOABBox = Box.createHorizontalBox();
        estadoOABBox.add(estadoOABLabel);
        estadoOABBox.add(estadoOAB);

        // ===== CPF =====
        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField(11);
        cpfField.setInputVerifier(new CPFInputVerifier());
        Box cpfBox = Box.createHorizontalBox();
        cpfBox.add(cpfLabel);
        cpfBox.add(cpfField);

        // ===== NOME =====
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();
        Box nomeBox = Box.createHorizontalBox();
        nomeBox.add(nomeLabel);
        nomeBox.add(nomeField);

        // ===== SENHA =====
        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaField = new JPasswordField();
        Box senhaBox = Box.createHorizontalBox();
        senhaBox.add(senhaLabel);
        senhaBox.add(senhaField);

        // ===== COMARCA =====
        JLabel comarcaLabel = new JLabel("Comarca:");
        JTextField comarcaField = new JTextField();
        Box comarcaBox = Box.createHorizontalBox();
        comarcaBox.add(comarcaLabel);
        comarcaBox.add(comarcaField);

        // ===== NUMERO OAB =====
        JLabel numeroOABLabel = new JLabel("Número OAB:");
        JTextField numeroOABField = new JTextField(6);
        numeroOABField.setInputVerifier(new NumeroOABInputVerifier());
        Box numeroOABBox = Box.createHorizontalBox();
        numeroOABBox.add(numeroOABLabel);
        numeroOABBox.add(numeroOABField);

        // Definindo quais campos aparecem em cada tipo do usuário
        // Atualizado sempre que opção escolhida em tipoUsuario é alterada
        tipoUsuario.addActionListener(e -> {
            String tipo = tipoUsuario.getSelectedItem().toString();
            switch (tipo) {
                case "Administrador", "Diretor" -> {
                    estadoOABBox.setVisible(false);
                    comarcaBox.setVisible(false);
                    numeroOABBox.setVisible(false);
                }
                case "Advogado" -> {
                    estadoOABBox.setVisible(true);
                    comarcaBox.setVisible(false);
                    numeroOABBox.setVisible(true);
                }
                case "Juiz", "Promotor" -> {
                    estadoOABBox.setVisible(false);
                    comarcaBox.setVisible(true);
                    numeroOABBox.setVisible(false);
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

        // Componentes do frame ficarão em um box vertical
        Box verticalBox = Box.createVerticalBox();
        // Adicionando na seguinte ordem: Tipo, CPF, Nome, Senha, Comarca, Número OAB e Estado OAB
        verticalBox.add(Box.createVerticalStrut(10));
        verticalBox.add(tipoUsuarioBox);
        verticalBox.add(Box.createVerticalStrut(10));
        verticalBox.add(cpfBox);
        verticalBox.add(Box.createVerticalStrut(10));
        verticalBox.add(nomeBox);
        verticalBox.add(Box.createVerticalStrut(10));
        verticalBox.add(senhaBox);
        verticalBox.add(Box.createVerticalStrut(10));
        verticalBox.add(comarcaBox);
        verticalBox.add(Box.createVerticalStrut(10));
        verticalBox.add(numeroOABBox);
        verticalBox.add(Box.createVerticalStrut(10));
        verticalBox.add(estadoOABBox);
        verticalBox.add(Box.createVerticalStrut(10));
        verticalBox.add(cadastrarButton);
        verticalBox.add(Box.createVerticalStrut(10));

        frame.getContentPane().add(verticalBox, BorderLayout.CENTER);
        return frame;
    }
}
