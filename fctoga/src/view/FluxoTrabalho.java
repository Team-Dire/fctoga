package view;

import controllers.FCToga;
import models.Anexo;
import models.Minuta;
import models.Processo;
import models.Usuario;
import view.anexos.ListarAnexos;
import view.processos.CriarProcesso;

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

        // Tabela de fluxo de trabalho
        JTable fluxoTrabalho = new JTable();
        DefaultTableModel fluxoTrabalhoModel = new DefaultTableModel(COLUNAS, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public int getRowCount() {
                return atualizaListaProcessos().size();
            }

            @Override
            public Object getValueAt(int row, int column) {
                // Anexo com data de modificação mais recente
                Anexo anexoMaisRecente = atualizaListaProcessos().get(row).getAnexoModificacaoMaisRecente();
                // Tipo de anexo: Despacho, Petição ou Minuta de Sentença
                String tipoAnexoMaisRecente;
                if (anexoMaisRecente == null)
                    tipoAnexoMaisRecente = "Petição";
                else if (anexoMaisRecente instanceof Minuta)
                    tipoAnexoMaisRecente = String.format("Minuta de %s", ((Minuta) anexoMaisRecente).getTipoMinuta());
                else
                    tipoAnexoMaisRecente = "Petição";
                Date dataUltimaModificacao = atualizaListaProcessos().get(row).getDataUltimaModificacao();

                return switch(column) {
                    case 0 -> atualizaListaProcessos().get(row).getNumeroProcesso();
                    // Tipo do anexo mais recente: Despacho, Petição ou Minuta de Sentença
                    case 1 -> tipoAnexoMaisRecente;
                    case 2 -> dataUltimaModificacao;
                    case 3 -> atualizaListaProcessos().get(row).getFechado() ? "Fechado" : "Aberto";
                    default -> null;
                };
            }
        };
        fluxoTrabalho.setModel(fluxoTrabalhoModel);
        JScrollPane fluxoTrabalhoScrollPane = new JScrollPane(fluxoTrabalho);

        JButton criarProcesso = new JButton("Criar processo");
        criarProcesso.addActionListener(e -> {
            JFrame frameCriarProcesso = CriarProcesso.render(fluxoTrabalhoModel);
            frameCriarProcesso.setVisible(true);
        });

        JButton visualizarHistorico = new JButton("Visualizar histórico do processo");
        visualizarHistorico.addActionListener(e -> {
            int row = fluxoTrabalho.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Selecione um processo para visualizar seus anexos.");
                return;
            }
            Processo processoSelecionado = atualizaListaProcessos().get(row);
            JFrame frameListarAnexos = ListarAnexos.render(processoSelecionado, fluxoTrabalhoModel);
            frameListarAnexos.setVisible(true);
        });

        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        frame.add(fluxoTrabalhoScrollPane, c);
        c.gridwidth = 1;
        c.gridx = 0; c.gridy = 1; frame.add(visualizarHistorico, c);
        c.gridx = 1; c.gridy = 1; frame.add(criarProcesso, c);

        frame.pack(); frame.setMinimumSize(frame.getSize());
        return frame;
    }

    private static List<Processo> atualizaListaProcessos() {
        List<Processo> processosFCToga = FCToga.getInstance().processos;
        Usuario usuarioLogado = FCToga.getInstance().getUsuarioLogado();
        String tipoUsuarioLogado = usuarioLogado.getTipoUsuario();
        Stream<Processo> processosStream;

        switch (tipoUsuarioLogado) {
            case "Advogado", "Promotor" -> processosStream = processosFCToga
                    .stream()
                    .filter(p -> p.getRepresentanteRequerente() == usuarioLogado || p.getRepresentanteRequerido() == usuarioLogado);
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
        Stream<Processo> processoStreamSorted = processosStream.sorted(Comparator.comparing(Processo::getDataUltimaModificacao).reversed());
        return processoStreamSorted.toList();
    }
}
