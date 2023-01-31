package view.anexos;

import controllers.FCToga;
import models.Processo;

import javax.swing.*;
import java.awt.*;

public class CriarPeticao {
    public static JFrame render(Processo processo) {
        JFrame frame = new JFrame("Adicionar Petição");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);

        // Campo para inserir texto da petição
        JLabel textoPeticaoLabel = new JLabel("Texto da petição");
        JTextArea textoPeticaoField = new JTextArea(20, 20);
        textoPeticaoField.setMinimumSize(textoPeticaoField.getPreferredSize());
        JScrollPane textoPeticaoFieldScrollPane = new JScrollPane(textoPeticaoField);
        c.gridx = 0; c.gridy = 0;
        frame.add(textoPeticaoLabel, c);
        c.gridx = 1;
        frame.add(textoPeticaoFieldScrollPane, c);

        // Botão de adicionar
        JButton adicionar = new JButton("Adicionar");
        adicionar.addActionListener(e -> {
            if (textoPeticaoField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Digite o texto da petição");
                return;
            }

            try {
                // Adiciona petição
                processo.adicionarPeticao(textoPeticaoField.getText());
                FCToga.serializeInstance();
            }
            catch (Exception exception) {
                JOptionPane.showMessageDialog(frame, exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(frame, "Petição adicionada com sucesso");
            frame.dispose();
        });
        c.gridx = 0; c.gridy = 1; c.gridwidth = 2;
        frame.add(adicionar, c);

        frame.pack();
        frame.setMinimumSize(frame.getSize());

        return frame;
    }
}
