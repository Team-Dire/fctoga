package view.anexos;

import models.Minuta;

import javax.swing.*;

public class VisualizarMinuta {
    private final static String[] LABELS = {"Data de criação", "Data de última modificação", "Tipo da minuta", "Texto da minuta"};

    public static JFrame render(Minuta minuta) {
        JFrame frame = new JFrame("Visualizar Minuta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 900);

        JLabel[] labels = new JLabel[LABELS.length + 2];
        JTextField[] conteudos = new JTextField[LABELS.length + 2];
        Box[] horizontalBoxes = new Box[LABELS.length + 2];

        for (int i = 0; i < LABELS.length; i++) {
            horizontalBoxes[i] = Box.createHorizontalBox();
            labels[i] = new JLabel(LABELS[i]);
            conteudos[i] = new JTextField();
            conteudos[i].setEditable(false);
            conteudos[i].setBorder(null);
            conteudos[i].setOpaque(false);
            conteudos[i].setFocusable(false);
            conteudos[i].setColumns(30);
            horizontalBoxes[i].add(labels[i]);
            horizontalBoxes[i].add(conteudos[i]);
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
            horizontalBoxes[LABELS.length + 1] = Box.createHorizontalBox();
            horizontalBoxes[LABELS.length + 1].add(labels[LABELS.length + 1]);
            horizontalBoxes[LABELS.length + 1].add(conteudos[LABELS.length + 1]);

            labels[LABELS.length + 2] = new JLabel("Assinatura");
            conteudos[LABELS.length + 2] = new JTextField();
            conteudos[LABELS.length + 2].setEditable(false);
            conteudos[LABELS.length + 2].setBorder(null);
            conteudos[LABELS.length + 2].setOpaque(false);
            conteudos[LABELS.length + 2].setFocusable(false);
            conteudos[LABELS.length + 2].setColumns(30);
            conteudos[LABELS.length + 2].setText(minuta.getNomeJuiz() + " - " + minuta.getComarcaJuiz());
            horizontalBoxes[LABELS.length + 2] = Box.createHorizontalBox();
            horizontalBoxes[LABELS.length + 2].add(labels[LABELS.length + 2]);
            horizontalBoxes[LABELS.length + 2].add(conteudos[LABELS.length + 2]);
        }

        // Box Layout vertical
        Box verticalBox = Box.createVerticalBox();
        // Adiciona os horizontal boxes
        for (Box horizontalBox : horizontalBoxes) {
            // Espaçamento vertical
            verticalBox.add(Box.createVerticalStrut(10));
            verticalBox.add(horizontalBox);
        }

        return frame;
    }
}
