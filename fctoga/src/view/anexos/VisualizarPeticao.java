package view.anexos;

import com.github.rjeschke.txtmark.Processor;
import models.Advogado;
import models.Peticao;
import models.Usuario;

import javax.swing.*;
import java.awt.*;

public class VisualizarPeticao {
    public static JFrame render(String numeroProcesso, Peticao peticao) {
        Usuario autorPeticao = peticao.getAutorPeticao();
        boolean isAdvogado = autorPeticao instanceof Advogado;
        String markdown = String.format(
                """
                        # Processo %s
                        Data de criação: %s

                        Data de última alteração: %s

                        Autor da petição: %s

                        ---

                        %s
                        """,
                numeroProcesso,
                peticao.getDataCriacao(),
                peticao.getDataUltimaModificacao(),
                isAdvogado ?
                        String.format("%s - %s%s",
                                autorPeticao.getNomeCompleto(),
                                ((Advogado) autorPeticao).getEstadoOAB(),
                                ((Advogado) autorPeticao).getNumeroOAB()
                        ) :
                        autorPeticao.getNomeCompleto(),
                peticao.getTextoPeticao()
        );

        JFrame frame = new JFrame("Visualizar petição");
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
