package view.test;

import controllers.FCToga;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

public class Cadastro {
    public static JFrame get() {
        JFrame frame = new JFrame("Cadastro");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 600);
        // Label e JComboBox para escolher o tipo de usuário
        JLabel tipoUsuarioLabel = new JLabel("Tipo de usuário:");
        JComboBox<String> tipoUsuario = new JComboBox<>();
        // Itens são os tipos: Administrador, Advogado, Diretor, Juiz e Promotor
        tipoUsuario.addItem("Administrador");
        tipoUsuario.addItem("Advogado");
        tipoUsuario.addItem("Diretor");
        tipoUsuario.addItem("Juiz");
        tipoUsuario.addItem("Promotor");

        // Label e JComboBox para escolher o Estado OAB
        JLabel estadoOABLabel = new JLabel("Estado OAB:");
        JComboBox<String> estadoOAB = new JComboBox<>();
        // Itens são os 27 estados brasileiros (incluso DF)
        estadoOAB.addItem("AC");
        estadoOAB.addItem("AL");
        estadoOAB.addItem("AP");
        estadoOAB.addItem("AM");
        estadoOAB.addItem("BA");
        estadoOAB.addItem("CE");
        estadoOAB.addItem("DF");
        estadoOAB.addItem("ES");
        estadoOAB.addItem("GO");
        estadoOAB.addItem("MA");
        estadoOAB.addItem("MT");
        estadoOAB.addItem("MS");
        estadoOAB.addItem("MG");
        estadoOAB.addItem("PA");
        estadoOAB.addItem("PB");
        estadoOAB.addItem("PR");
        estadoOAB.addItem("PE");
        estadoOAB.addItem("PI");
        estadoOAB.addItem("RJ");
        estadoOAB.addItem("RN");
        estadoOAB.addItem("RS");
        estadoOAB.addItem("RO");
        estadoOAB.addItem("RR");
        estadoOAB.addItem("SC");
        estadoOAB.addItem("SP");
        estadoOAB.addItem("SE");
        estadoOAB.addItem("TO");

        // Label e TextField para cada um dos atributos:
        // CPF, Nome, Senha, Comarca e Número OAB
        // CPF e Número OAB são Strings que aceitam apenas números.
        // Para isso, utiliza o NumberFormatter
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Long.class);
        numberFormatter.setAllowsInvalid(false);
        // Senha é um JPasswordField
        JLabel cpfLabel = new JLabel("CPF:");
        JFormattedTextField cpfField = new JFormattedTextField(numberFormatter);
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();
        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaField = new JPasswordField();
        JLabel comarcaLabel = new JLabel("Comarca:");
        JTextField comarcaField = new JTextField();
        JLabel numeroOABLabel = new JLabel("Número OAB:");
        JFormattedTextField numeroOABField = new JFormattedTextField(numberFormatter);

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
        });

        // Adicionando na seguinte ordem: Tipo, CPF, Nome, Senha, Comarca, Número OAB e Estado OAB
        // Label sempre primeiro
        // Por fim adiciona o botão
        frame.getContentPane().add(tipoUsuarioLabel);
        frame.getContentPane().add(tipoUsuario);
        frame.getContentPane().add(cpfLabel);
        frame.getContentPane().add(cpfField);
        frame.getContentPane().add(nomeLabel);
        frame.getContentPane().add(nomeField);
        frame.getContentPane().add(senhaLabel);
        frame.getContentPane().add(senhaField);
        frame.getContentPane().add(comarcaLabel);
        frame.getContentPane().add(comarcaField);
        frame.getContentPane().add(numeroOABLabel);
        frame.getContentPane().add(numeroOABField);
        frame.getContentPane().add(estadoOABLabel);
        frame.getContentPane().add(estadoOAB);
        frame.getContentPane().add(cadastrarButton);

        // Layout é Box Layout com eixo vertical
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        return frame;
    }
}
