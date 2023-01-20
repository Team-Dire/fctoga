package view.test;

import models.Minuta;
import models.Peticao;

import javax.swing.*;

public class VisualizarAnexo {
    public static JFrame visualizarMinuta(Minuta minuta) {
        JFrame frame = new JFrame("Visualizar Minuta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 900);

        // Box Layout vertical
        Box verticalBox = Box.createVerticalBox();
        // Data de criação
        JLabel dataCriacaoLabel = new JLabel("Data de criação: " + minuta.getDataCriacao().toString());
        verticalBox.add(dataCriacaoLabel);
        // Data de última modificação
        JLabel dataUltimaModificacaoLabel = new JLabel("Data de última modificação: " + minuta.getDataUltimaModificacao().toString());
        verticalBox.add(dataUltimaModificacaoLabel);
        // Tipo da minuta
        JLabel tipoMinutaLabel = new JLabel("Tipo da minuta: " + minuta.getTipoMinuta());
        verticalBox.add(tipoMinutaLabel);
        // Texto da minuta
        JLabel textoMinutaLabel = new JLabel("Texto da minuta: " + minuta.getTextoMinuta());
        verticalBox.add(textoMinutaLabel);
        if (minuta.getAssinada()) {
            // Assinada
            JLabel assinadaLabel = new JLabel("Assinada: " + (minuta.getAssinada() ? "Sim" : "Não"));
            verticalBox.add(assinadaLabel);
            // Assinatura
            JLabel assinaturaLabel = new JLabel("Assinatura: " + minuta.getNomeJuiz() + " - " + minuta.getComarcaJuiz());
            verticalBox.add(assinaturaLabel);
        }

        // ComponentPane do Frame, box vertical
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().add(verticalBox);
        return frame;
    }

    public static JFrame visualizarPeticao(Peticao peticao) {
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
