package view.processos;

import controllers.ControladorProcesso;

import javax.swing.*;
import java.awt.*;

public class CriarProcesso {
    private static final String[] LABELS = {"CPF/CNPJ do Requerente", "Nome do Requerente", "CPF/CNPJ do Requerido", "Nome do Requerido"};

    public static JFrame render() {
        JFrame frame = new JFrame("Criar Processo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 400);

        // ===== CAMPOS DE ENTRADA =====
        JLabel[] labels = new JLabel[LABELS.length];
        JTextField[] textFields = new JTextField[LABELS.length];
        Box[] fieldHorizontalBoxes = new Box[LABELS.length];
        for (int i = 0; i < LABELS.length; i++) {
            labels[i] = new JLabel(LABELS[i]);
            textFields[i] = new JTextField();
            fieldHorizontalBoxes[i] = Box.createHorizontalBox();
            fieldHorizontalBoxes[i].add(labels[i]);
            fieldHorizontalBoxes[i].add(Box.createHorizontalStrut(10));
            fieldHorizontalBoxes[i].add(textFields[i]);
        }
        // ===== CAMPOS DE ENTRADA =====

        // ===== BOTÃO DE CRIAR PROCESSO =====
        JButton criarProcessoButton = new JButton("Criar Processo");
        criarProcessoButton.addActionListener(e -> {
            boolean sucesso;
            try {
                for (JTextField field : textFields) {
                    if (field.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                ControladorProcesso.novoProcesso(
                        textFields[0].getText(),
                        textFields[1].getText(),
                        textFields[2].getText(),
                        textFields[3].getText()
                );
                sucesso = true;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
                sucesso = false;
            }
            if (sucesso) {
                JOptionPane.showMessageDialog(frame, "Processo criado com sucesso!");
                frame.dispose();
            }
        });
        // ===== BOTÃO DE CRIAR PROCESSO =====

        // BoxLayout vertical
        Box verticalBox = Box.createVerticalBox();
        for (int i = 0; i < labels.length; i++) {
            verticalBox.add(Box.createVerticalStrut(10));
            verticalBox.add(fieldHorizontalBoxes[i]);
        }

        Box botaoBox = Box.createHorizontalBox();
        botaoBox.add(criarProcessoButton);
        verticalBox.add(botaoBox);

        frame.getContentPane().add(verticalBox, BorderLayout.CENTER);
        return frame;
    }
}
