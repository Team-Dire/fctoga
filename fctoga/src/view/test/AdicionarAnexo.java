package view.test;

import models.Processo;

import javax.swing.*;

public class AdicionarAnexo {
    // Dialog para adicionar anexo, seleciona entre minuta e petição
    public static int selecionaTipo() {
        return JOptionPane.showOptionDialog(null, "Selecione o tipo de anexo", "Adicionar Anexo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Minuta", "Petição"}, null);
    }

    // Frame para adicionar minuta
    public static JFrame getAdicionarMinuta(Processo processo) {
        JFrame frame = new JFrame("Adicionar Minuta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 900);

        // Campo para inserir tipo da minuta
        JLabel tipoLabel = new JLabel("Tipo da minuta");
        JTextField tipo = new JTextField();

        // Campo para inserir texto da minuta
        JLabel textoLabel = new JLabel("Texto da minuta");
        JTextArea texto = new JTextArea();

        // Botão de adicionar
        JButton adicionar = new JButton("Adicionar");
        adicionar.addActionListener(e -> {
            if (tipo.getText().isEmpty() || texto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos");
                return;
            }

            // Adiciona minuta
            processo.adicionarMinuta(tipo.getText(), texto.getText());
            JOptionPane.showMessageDialog(frame, "Minuta adicionada com sucesso");
            frame.dispose();
        });

        // Adiciona componentes ao frame
        // Box layout horizontal para cada campo
        Box tipoBox = Box.createHorizontalBox();
        tipoBox.add(tipoLabel);
        tipoBox.add(tipo);

        Box textoBox = Box.createHorizontalBox();
        textoBox.add(textoLabel);
        textoBox.add(texto);

        // Box layout vertical para os campos e o botão
        Box box = Box.createVerticalBox();
        box.add(tipoBox);
        box.add(textoBox);
        box.add(adicionar);

        frame.getContentPane().add(box);
        return frame;
    }

    // Frame para adicionar petição
    public static JFrame getAdicionarPeticao(Processo processo) {
        JFrame frame = new JFrame("Adicionar Petição");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 900);

        // Campo para inserir texto da petição
        JLabel textoLabel = new JLabel("Texto da petição");
        JTextArea texto = new JTextArea();

        // Botão de adicionar
        JButton adicionar = new JButton("Adicionar");
        adicionar.addActionListener(e -> {
            if (texto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos");
                return;
            }

            // Adiciona petição
            processo.adicionarPeticao(texto.getText());
            JOptionPane.showMessageDialog(frame, "Petição adicionada com sucesso");
            frame.dispose();
        });

        // Adiciona componentes ao frame
        // Box layout horizontal para cada campo
        Box textoBox = Box.createHorizontalBox();
        textoBox.add(textoLabel);
        textoBox.add(texto);

        // Box layout vertical para os campos e o botão
        Box box = Box.createVerticalBox();
        box.add(textoBox);
        box.add(adicionar);

        frame.getContentPane().add(box);
        return frame;
    }
}
