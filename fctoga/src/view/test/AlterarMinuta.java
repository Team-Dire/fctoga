package view.test;

import models.Minuta;

import javax.swing.*;

public class AlterarMinuta {
    public static JFrame get(Minuta instanciaMinuta) {
        JFrame frame = new JFrame("Alterar Minuta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 900);

        // ========== Formulário ==========
        JPanel linhaTextoMinuta = new JPanel();
        linhaTextoMinuta.setLayout(new BoxLayout(linhaTextoMinuta, BoxLayout.X_AXIS));
        JLabel textoMinutaLabel = new JLabel("Texto da minuta");
        JTextArea textoMinuta = new JTextArea(instanciaMinuta.getTextoMinuta());
        textoMinuta.setLineWrap(true);
        textoMinuta.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textoMinuta);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        linhaTextoMinuta.add(textoMinutaLabel);
        linhaTextoMinuta.add(scrollPane);
        // ========== Formulário ==========

        // Botão de salvar
        JButton salvar = new JButton("Salvar");
        salvar.addActionListener(e -> {
            Boolean minutaAlterada = instanciaMinuta.alterarMinuta(textoMinuta.getText());
            if (minutaAlterada) {
                JOptionPane.showMessageDialog(frame, "Minuta alterada com sucesso");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Erro ao alterar minuta");
            }
        });

        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().add(linhaTextoMinuta);
        frame.getContentPane().add(salvar);
        return frame;
    }
}
