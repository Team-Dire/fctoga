package view.anexos;

import controllers.FCToga;
import models.Processo;

import javax.swing.*;

public class CriarMinuta {
    private static final String[] LABELS = {"Tipo da minuta", "Texto da minuta"};

    public static JFrame render(Processo processo) {
        JFrame frame = new JFrame("Criar Minuta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 900);

        // ===== CAMPOS =====
        JLabel[] labels = new JLabel[2];
        JTextField[] textFields = new JTextField[2];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(LABELS[i]);
            textFields[i] = new JTextField();
        }

        // Botão de adicionar
        JButton adicionar = new JButton("Criar minuta");
        adicionar.addActionListener(e -> {
            for (JTextField campo : textFields) {
                if (campo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Adiciona minuta
            processo.adicionarMinuta(textFields[0].getText(), textFields[1].getText());
            FCToga.serializeInstance();
            JOptionPane.showMessageDialog(frame, "Minuta adicionada com sucesso");
            frame.dispose();
        });

        // Adiciona componentes ao frame
        // Box layout horizontal para cada campo
        // Box layout vertical para o frame em si
        Box verticalBox = Box.createVerticalBox();
        for (int i = 0; i < textFields.length; i++) {
            Box box = Box.createHorizontalBox();
            box.add(labels[i]);
            box.add(Box.createHorizontalStrut(10));
            box.add(textFields[i]);
            verticalBox.add(box);
            verticalBox.add(Box.createVerticalStrut(10));
        }

        // Horizontal box para o botão
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(adicionar);
        verticalBox.add(buttonBox);

        frame.getContentPane().add(verticalBox);
        return frame;
    }
}
