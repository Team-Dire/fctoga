package view.anexos;

import com.github.rjeschke.txtmark.Processor;
import models.Minuta;

import javax.swing.*;
import java.awt.*;

public class VisualizarMinuta {
    public static JFrame render(String numeroProcesso, Minuta minuta) {
        String markdown = String.format(
                """
                        # Processo %s
                        Data de criação: %s

                        Data de última alteração: %s

                        Autor: %s

                        Minuta de %s

                        ---

                        %s
                        """, numeroProcesso, minuta.getDataCriacao(), minuta.getDataUltimaModificacao(), minuta.getAutorMinuta().getNomeCompleto(), minuta.getTipoMinuta(), minuta.getTextoMinuta());

        if (minuta.getAssinada()) {
            markdown +=
                    String.format("""

                            ---

                            Assinada digitalmente por %s, comarca de %s.
                            """, minuta.getNomeJuiz(), minuta.getComarcaJuiz());
        }
        JFrame frame = new JFrame("Visualizar minuta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);

        JEditorPane minutaEditorPane = new JEditorPane();
        minutaEditorPane.setContentType("text/html");
        minutaEditorPane.setText(Processor.process(markdown));
        minutaEditorPane.setEditable(false);
        JScrollPane minutaScrollPane = new JScrollPane(minutaEditorPane);

        c.gridx = 0;
        c.gridy = 0;
        frame.add(minutaScrollPane, c);

        frame.pack();
        frame.setMinimumSize(frame.getSize());

        return frame;
    }
}
