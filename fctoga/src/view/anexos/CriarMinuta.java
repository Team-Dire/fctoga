package view.anexos;

import controllers.FCToga;
import models.Processo;

import javax.swing.*;
import java.util.Arrays;

public class CriarMinuta {
    private static final String[] TIPOS_MINUTA = {"Despacho", "Decisão", "Sentença"};

    public static JFrame render(Processo processo) {
        JFrame frame = new JFrame("Criar Minuta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 900);

        // ===== CAMPOS =====
        // Tipo da minuta
        JLabel tipoLabel = new JLabel("Tipo da minuta");
        JComboBox<String> tipoField = new JComboBox<>();
        Arrays.stream(TIPOS_MINUTA).forEach(tipoField::addItem);
        Box tipoBox = Box.createHorizontalBox(); tipoBox.add(tipoLabel); tipoBox.add(tipoField);

        // Texto da minuta
        JLabel textoLabel = new JLabel("Texto da minuta");
        JTextArea textoField = new JTextArea();
        Box textoBox = Box.createHorizontalBox(); textoBox.add(textoLabel); textoBox.add(textoField);

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
            processo.adicionarMinuta(tipoField.getSelectedItem().toString(), textoField.getText());
            FCToga.serializeInstance();
            JOptionPane.showMessageDialog(frame, "Minuta adicionada com sucesso");
            frame.dispose();
        });
        Box adicionarBox = Box.createHorizontalBox(); adicionarBox.add(adicionar);

        // Adiciona componentes ao frame
        // Box layout horizontal para cada campo
        // Box layout vertical para o frame em si
        Box frameBox = Box.createVerticalBox();
        frameBox.add(Box.createVerticalStrut(10));
        frameBox.add(tipoBox);
        frameBox.add(Box.createVerticalStrut(10));
        frameBox.add(textoBox);
        frameBox.add(Box.createVerticalStrut(10));
        frameBox.add(adicionarBox);
        frameBox.add(Box.createVerticalStrut(10));

        frame.getContentPane().add(frameBox);
        return frame;
    }
}
