package view.anexos;

import controllers.FCToga;
import models.Processo;

import javax.swing.*;

public class CriarPeticao {
    private static final String[] LABELS = {"Texto da petição"};

    public static JFrame render(Processo processo) {
        JFrame frame = new JFrame("Adicionar Petição");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 900);

        // Campo para inserir texto da petição
        JLabel[] labels = new JLabel[LABELS.length];
        JTextField[] textFields = new JTextField[LABELS.length];
        for (int i = 0; i < LABELS.length; i++) {
            labels[i] = new JLabel(LABELS[i]);
            textFields[i] = new JTextField();
        }

        // Botão de adicionar
        JButton adicionar = new JButton("Adicionar");
        adicionar.addActionListener(e -> {
            for (int i = 0; i < LABELS.length; i++) {
                if (textFields[i].getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Adiciona petição
            processo.adicionarPeticao(textFields[0].getText());
            FCToga.serializeInstance();
            JOptionPane.showMessageDialog(frame, "Petição adicionada com sucesso");
            frame.dispose();
        });

        // Adiciona componentes ao frame
        // Box layout horizontal para cada campo
        Box verticalBox = Box.createVerticalBox();
        for (int i = 0; i < LABELS.length; i++) {
            Box horizontalBox = Box.createHorizontalBox();
            horizontalBox.add(labels[i]);
            horizontalBox.add(textFields[i]);
            verticalBox.add(horizontalBox);
        }
        // Box horizontal para o botão
        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(adicionar);
        verticalBox.add(horizontalBox);

        frame.getContentPane().add(verticalBox);
        return frame;
    }
}
