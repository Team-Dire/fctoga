package view.anexos;

import models.Minuta;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VisualizarMinuta {
    private final static String[] LABELS = {"Data de criação", "Data de última modificação", "Tipo da minuta", "Texto da minuta"};

    public static JFrame render(Minuta minuta) {
        JFrame frame = new JFrame("Visualizar Minuta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 900);

        JLabel[] labels = new JLabel[LABELS.length + 2];
        JTextField[] conteudos = new JTextField[LABELS.length + 2];
        List<Box> horizontalBoxes = new ArrayList<Box>();

        for (int i = 0; i < LABELS.length; i++) {
            Box horizontalBox = Box.createHorizontalBox();
            labels[i] = new JLabel(LABELS[i]);
            conteudos[i] = new JTextField();
            conteudos[i].setEditable(false);
            conteudos[i].setBorder(null);
            conteudos[i].setOpaque(false);
            conteudos[i].setFocusable(false);
            conteudos[i].setColumns(30);
            horizontalBox.add(labels[i]);
            horizontalBox.add(conteudos[i]);
            horizontalBoxes.add(horizontalBox);
        }

        conteudos[0].setText(minuta.getDataCriacao().toString());
        conteudos[1].setText(minuta.getDataUltimaModificacao().toString());
        conteudos[2].setText(minuta.getTipoMinuta());
        conteudos[3].setText(minuta.getTextoMinuta());

        if (minuta.getAssinada()) {
            labels[LABELS.length + 1] = new JLabel("Assinada");
            conteudos[LABELS.length + 1] = new JTextField();
            conteudos[LABELS.length + 1].setEditable(false);
            conteudos[LABELS.length + 1].setBorder(null);
            conteudos[LABELS.length + 1].setOpaque(false);
            conteudos[LABELS.length + 1].setFocusable(false);
            conteudos[LABELS.length + 1].setColumns(30);
            conteudos[LABELS.length + 1].setText("Sim");
            Box horizontalBox = Box.createHorizontalBox();
            horizontalBox.add(labels[LABELS.length + 1]);
            horizontalBox.add(conteudos[LABELS.length + 1]);
            horizontalBoxes.add(horizontalBox);

            labels[LABELS.length + 2] = new JLabel("Assinatura");
            conteudos[LABELS.length + 2] = new JTextField();
            conteudos[LABELS.length + 2].setEditable(false);
            conteudos[LABELS.length + 2].setBorder(null);
            conteudos[LABELS.length + 2].setOpaque(false);
            conteudos[LABELS.length + 2].setFocusable(false);
            conteudos[LABELS.length + 2].setColumns(30);
            conteudos[LABELS.length + 2].setText(minuta.getNomeJuiz() + " - " + minuta.getComarcaJuiz());
            horizontalBox = Box.createHorizontalBox();
            horizontalBox.add(labels[LABELS.length + 2]);
            horizontalBox.add(conteudos[LABELS.length + 2]);
            horizontalBoxes.add(horizontalBox);
        }

        // Box Layout vertical
        Box verticalBox = Box.createVerticalBox();
        // Adiciona os horizontal boxes
        for (Box horizontalBox : horizontalBoxes) {
            // Espaçamento vertical
            verticalBox.add(Box.createVerticalStrut(10));
            verticalBox.add(horizontalBox);
        }

        frame.getContentPane().add(verticalBox);
        return frame;
    }
}
