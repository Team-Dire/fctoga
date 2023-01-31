package view.anexos;

import controllers.FCToga;
import models.Minuta;

import javax.swing.*;
import java.awt.*;

public class EditarMinuta {

    public static JFrame render(Minuta instanciaMinuta) {
        JFrame frame = new JFrame("Alterar Minuta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);

        // region TextoMinuta
        JLabel textoMinutaLabel = new JLabel("Texto da minuta");
        JTextArea textoMinuta = new JTextArea(20, 20);
        textoMinuta.setText(instanciaMinuta.getTextoMinuta());
        textoMinuta.setMinimumSize(textoMinuta.getPreferredSize());
        JScrollPane scrollPaneTextoMinuta = new JScrollPane(textoMinuta);
        c.gridx = 0; c.gridy = 0;
        frame.add(textoMinutaLabel, c);
        c.gridx = 1;
        frame.add(scrollPaneTextoMinuta, c);
        //endregion

        // region BotaoSalvar
        JButton salvar = new JButton("Salvar");
        salvar.addActionListener(e -> {
            if (textoMinuta.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O texto da minuta não pode ser vazio");
                return;
            }

            boolean minutaAlterada = instanciaMinuta.alterarMinuta(textoMinuta.getText());
            if (minutaAlterada) {
                FCToga.serializeInstance();
                JOptionPane.showMessageDialog(frame, "Minuta alterada com sucesso");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Minuta não foi alterada, verifique se a mesma já foi assinada.");
            }
        });
        c.gridx = 0; c.gridy = 1; c.gridwidth = 2;
        frame.add(salvar, c);
        //endregion

        frame.pack();
        frame.setMinimumSize(frame.getSize());

        return frame;
    }
}
