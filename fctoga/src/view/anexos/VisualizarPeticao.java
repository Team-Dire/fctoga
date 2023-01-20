package view.anexos;

import models.Peticao;

import javax.swing.*;

public class VisualizarPeticao {
    public static JFrame render(Peticao peticao) {
        JFrame frame = new JFrame("Visualizar Petição");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 900);

        // Box Layout vertical
        Box verticalBox = Box.createVerticalBox();
        // Data de criação
        JLabel dataCriacaoLabel = new JLabel("Data de criação: " + peticao.getDataCriacao().toString());
        verticalBox.add(dataCriacaoLabel);
        // Data de última modificação
        JLabel dataUltimaModificacaoLabel = new JLabel("Data de última modificação: " + peticao.getDataUltimaModificacao().toString());
        verticalBox.add(dataUltimaModificacaoLabel);
        // Texto da petição
        JLabel textoPeticaoLabel = new JLabel("Texto da petição: " + peticao.getTextoPeticao());
        verticalBox.add(textoPeticaoLabel);

        // ComponentPane do Frame, box vertical
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().add(verticalBox);

        return frame;
    }
}
