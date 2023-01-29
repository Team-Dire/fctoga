package view;

import controllers.FCToga;
import models.Anexo;
import models.Minuta;
import models.Processo;
import models.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class FluxoTrabalho {
    private static final String[] COLUNAS = {"Número do processo", "Última atualização", "Data da última atualização", "Status do processo"};
    public static JFrame render() {
        JFrame frame = new JFrame("Fluxo de Trabalho");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List<Processo> processosFCToga = FCToga.getInstance().processos;
        Usuario usuarioLogado = FCToga.getInstance().getUsuarioLogado();
        String tipoUsuarioLogado = usuarioLogado.getTipoUsuario();
        Stream<Processo> processosStream;

        switch (tipoUsuarioLogado) {
            case "Advogado", "Promotor" -> processosStream = processosFCToga
                    .stream()
                    .filter(p -> p.getRepresentante() == usuarioLogado);
            // Processos com minutas a assinar
            case "Juiz" -> {
                processosStream = processosFCToga
                        .stream()
                        .filter(
                                p -> p.getAnexos()
                                        .stream()
                                        .filter(a -> a instanceof Minuta)
                                        .anyMatch(
                                            m -> !((Minuta) m)
                                                    .getAssinada())
                        );
            }
            case "Diretor" -> processosStream = processosFCToga.stream();
            default -> processosStream = Stream.empty();
        }

        // Ordenando processos por data de última atualização, caso hajam processos
        Stream<Processo> processoStreamSorted = processosStream.sorted(Comparator.comparing(Processo::getDataUltimaModificacao));
        List<Processo> processosList = processoStreamSorted.toList();

        // Tabela de fluxo de trabalho
        JTable fluxoTrabalho = new JTable();
        DefaultTableModel fluxoTrabalhoModel = new DefaultTableModel(COLUNAS, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public int getRowCount() {
                return processosList.size();
            }

            @Override
            public Object getValueAt(int row, int column) {
                // Anexo com data de modificação mais recente
                Anexo anexoMaisRecente = processosList.get(row).getAnexoModificacaoMaisRecente();
                // Tipo de anexo: Despacho, Petição ou Minuta de Sentença
                String tipoAnexoMaisRecente;
                if (anexoMaisRecente == null)
                    tipoAnexoMaisRecente = "Petição";
                else if (anexoMaisRecente instanceof Minuta)
                    tipoAnexoMaisRecente = String.format("Minuta de %s", ((Minuta) anexoMaisRecente).getTipoMinuta());
                else
                    tipoAnexoMaisRecente = "Petição";
                Date dataUltimaModificacao = processosList.get(row).getDataUltimaModificacao();

                return switch(column) {
                    case 0 -> processosList.get(row).getNumeroProcesso();
                    // Tipo do anexo mais recente: Despacho, Petição ou Minuta de Sentença
                    case 1 -> tipoAnexoMaisRecente;
                    case 2 -> dataUltimaModificacao;
                    case 3 -> processosList.get(row).getFechado() ? "Fechado" : "Aberto";
                    default -> null;
                };
            }
        };
        fluxoTrabalho.setModel(fluxoTrabalhoModel);
        JScrollPane fluxoTrabalhoScrollPane = new JScrollPane(fluxoTrabalho);

        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0; c.gridy = 0; frame.add(fluxoTrabalhoScrollPane, c);

        frame.pack(); frame.setMinimumSize(frame.getSize());
        return frame;
    }
}
