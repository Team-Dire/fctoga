package view.anexos;

import controllers.FCToga;
import models.Processo;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class CriarMinuta {
    private static final String[] TIPOS_MINUTA = {"Despacho", "Decisão", "Sentença"};

    public static JFrame render(Processo processo) {
        JFrame frame = new JFrame("Criar Minuta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);

        // ===== CAMPOS =====
        // Tipo da minuta
        JLabel tipoLabel = new JLabel("Tipo da minuta");
        JComboBox<String> tipoField = new JComboBox<>();
        Arrays.stream(TIPOS_MINUTA).forEach(tipoField::addItem);
        c.gridx = 0; c.gridy = 0;
        frame.add(tipoLabel, c);
        c.gridx = 1;
        frame.add(tipoField, c);

        // Texto da minuta
        JLabel textoLabel = new JLabel("Texto da minuta");
        JTextArea textoField = new JTextArea(20, 20);
        textoField.setMinimumSize(textoField.getPreferredSize());
        JScrollPane textoPeticaoFieldScrollPane = new JScrollPane(textoField);
        c.gridx = 0; c.gridy = 1;
        frame.add(textoLabel, c);
        c.gridx = 1;
        frame.add(textoPeticaoFieldScrollPane, c);

        // Botão de adicionar
        JButton adicionar = new JButton("Criar minuta");
        adicionar.addActionListener(e -> {
            if (tipoField.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(frame, "Selecione um tipo de minuta");
                return;
            }

            if (textoField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Digite o texto da minuta");
                return;
            }

            // Adiciona minuta
            try {
                processo.adicionarMinuta(tipoField.getSelectedItem().toString(), textoField.getText());
                FCToga.serializeInstance();
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
                return;
            }
            JOptionPane.showMessageDialog(frame, "Minuta adicionada com sucesso");
            frame.dispose();
        });
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        frame.add(adicionar, c);

        frame.pack();
        frame.setMinimumSize(frame.getSize());

        return frame;
    }
}
