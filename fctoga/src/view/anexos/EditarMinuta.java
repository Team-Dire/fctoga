package view.anexos;

import models.Minuta;

import javax.swing.*;

public class EditarMinuta {

    public static JFrame render(Minuta instanciaMinuta) {
        JFrame frame = new JFrame("Alterar Minuta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 900);

        // region TextoMinuta
        Box boxTextoMinuta = Box.createHorizontalBox();

        JLabel textoMinutaLabel = new JLabel("Texto da minuta");
        JTextArea textoMinuta = new JTextArea(instanciaMinuta.getTextoMinuta());
        textoMinuta.setLineWrap(true);
        textoMinuta.setWrapStyleWord(true);

        JScrollPane scrollPaneTextoMinuta = new JScrollPane(textoMinuta);
        scrollPaneTextoMinuta.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneTextoMinuta.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        boxTextoMinuta.add(textoMinutaLabel);
        boxTextoMinuta.add(scrollPaneTextoMinuta);
        //endregion

        // region BotaoSalvar
        JButton salvar = new JButton("Salvar");
        salvar.addActionListener(e -> {
            boolean minutaAlterada = instanciaMinuta.alterarMinuta(textoMinuta.getText());
            if (minutaAlterada) {
                JOptionPane.showMessageDialog(frame, "Minuta alterada com sucesso");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Erro ao alterar minuta");
            }
        });
        Box boxBotaoSalvar = Box.createHorizontalBox();
        boxBotaoSalvar.add(salvar);
        //endregion

        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(boxTextoMinuta);
        verticalBox.add(boxBotaoSalvar);

        frame.getContentPane().add(verticalBox);
        return frame;
    }
}
