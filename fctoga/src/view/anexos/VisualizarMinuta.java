package view.anexos;

import models.Minuta;

import javax.swing.*;
import java.awt.*;

public class VisualizarMinuta {
    private final static String[] LABELS = {"Data de criação", "Data de última modificação", "Tipo da minuta", "Texto da minuta"};

    public static JFrame render(Minuta minuta) {
        JFrame frame = new JFrame("Visualizar Minuta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        JLabel[] labels = new JLabel[LABELS.length + 2];
        JTextField[] conteudos = new JTextField[LABELS.length + 2];

        for (int i = 0; i < LABELS.length; i++) {
            labels[i] = new JLabel(LABELS[i]);
            conteudos[i] = new JTextField();
            conteudos[i].setEditable(false);
            conteudos[i].setBorder(null);
            conteudos[i].setOpaque(false);
            conteudos[i].setFocusable(false);
            conteudos[i].setColumns(30);
        }

        conteudos[0].setText(minuta.getDataCriacao().toString());
        conteudos[1].setText(minuta.getDataUltimaModificacao().toString());
        conteudos[2].setText(minuta.getTipoMinuta());
        conteudos[3].setText(minuta.getTextoMinuta());

        for (int i = 0; i < LABELS.length; i++) {
            c.gridy = i;
            c.gridx = 0;
            frame.add(labels[i], c);

            c.gridx = 1;
            frame.add(conteudos[i], c);
        }

        if (minuta.getAssinada()) {
            labels[LABELS.length] = new JLabel(String.format("Minuta assinada por Vossa Excelência %s da Comarca de %s", minuta.getNomeJuiz(), minuta.getComarcaJuiz()));

            c.gridy = LABELS.length;
            c.gridx = 0; c.gridwidth = 2;
            frame.add(labels[LABELS.length], c);
        }

        frame.pack();
        frame.setMinimumSize(frame.getSize());
        return frame;
    }
}
