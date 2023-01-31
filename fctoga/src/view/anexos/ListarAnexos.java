package view.anexos;

import controllers.FCToga;
import models.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ListarAnexos {
    private static final String[] COLUNAS = {"Data de criação", "Data de última modificação", "Tipo", "Assinada"};
    private static final String[] BOTOES_LABEL = {"Criar minuta", "Criar petição", "Visualizar", "Editar minuta", "Assinar minuta"};
    private static final Map<String, boolean[]> BOTOES_TIPO_USUARIO = Map.of(
            "Diretor", new boolean[]{true, false, true, true, false},
            "Juiz", new boolean[]{false, false, true, true, true},
            "Advogado", new boolean[]{false, true, true, false, false},
            "Promotor", new boolean[]{false, true, true, false, false}
    );

    public static JFrame render(Processo processo, DefaultTableModel fluxoTrabalhoModel) {
        ArrayList<Anexo> listaAnexos = processo.getAnexos();

        JFrame frame = new JFrame("Listar Anexos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // region Informações do Processo
        JPanel panelInformacoesProcesso = new JPanel();
        panelInformacoesProcesso.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 1, 0, 1);
        // Número do processo, data de criação e data de última modificação
        c.gridx = 0;
        c.gridy = 0;
        panelInformacoesProcesso.add(new JLabel(String.format("Número do processo: %s", processo.getNumeroProcesso())), c);
        c.gridy = 1;
        panelInformacoesProcesso.add(new JLabel(String.format("Data de criação: %s", processo.getDataCriacao())), c);
        c.gridy = 2;
        // Data de última modificação, verifica anexo com data de última modificação mais recente
        Date dataUltimaModificacao = processo.getDataCriacao();
        for (Anexo anexo : listaAnexos) {
            if (anexo.getDataUltimaModificacao().after(dataUltimaModificacao)) {
                dataUltimaModificacao = anexo.getDataUltimaModificacao();
            }
        }
        panelInformacoesProcesso.add(new JLabel(String.format("Data de última modificação: %s", dataUltimaModificacao)), c);
        // Requerente, requerido
        c.gridy = 3;
        panelInformacoesProcesso.add(new JLabel(String.format("Requerente: %s (%s)", processo.getNomeRequerente(), processo.getCPF_CNPJ_Requerente())), c);
        c.gridy = 4;
        panelInformacoesProcesso.add(new JLabel(String.format("Requerido: %s (%s)", processo.getNomeRequerido(), processo.getCPF_CNPJ_Requerido())), c);
        // Representante do requerente
        c.gridy = 5;
        String representanteRequerente;
        if (processo.getTipoProceso().equals("Criminal")) {
            representanteRequerente = "Representante (Requerente): Justiça Pública (511740010001)";
        } else {
            representanteRequerente = String.format("Representante (Requerente): %s (%s)", processo.getRepresentanteRequerente().getNomeCompleto(), processo.getRepresentanteRequerente().getCPF());
        }
        panelInformacoesProcesso.add(new JLabel(representanteRequerente), c);
        // Representante do requerido
        c.gridy = 6;
        panelInformacoesProcesso.add(new JLabel(String.format("Representante (Requerido): %s (%s)", processo.getRepresentanteRequerido().getNomeCompleto(), processo.getRepresentanteRequerido().getCPF())), c);
        //endregion

        // region Tabela
        JTable table = new JTable();

        AbstractTableModel model = new DefaultTableModel(COLUNAS, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public int getRowCount() {
                return listaAnexos.size();
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return switch (columnIndex) {
                    case 0 -> listaAnexos.get(rowIndex).getDataCriacao();
                    case 1 -> listaAnexos.get(rowIndex).getDataUltimaModificacao();
                    case 2 -> listaAnexos.get(rowIndex) instanceof Minuta ? "Minuta" : "Petição";
                    case 3 ->
                            listaAnexos.get(rowIndex) instanceof Minuta ? (((Minuta) listaAnexos.get(rowIndex)).getAssinada() ? "Assinada" : "Não assinada") : "N/A";
                    default -> null;
                };
            }
        };

        table.setModel(model);
        JScrollPane scrollPaneTabela = new JScrollPane(table);
        //endregion

        // region Botoes
        JButton[] botoes = new JButton[BOTOES_LABEL.length];
        for (int i = 0; i < BOTOES_LABEL.length; i++)
            botoes[i] = new JButton(BOTOES_LABEL[i]);

        botoes[0].addActionListener(e -> {
            if (processo.getFechado()) {
                JOptionPane.showMessageDialog(null, "Não é possível criar minutas em um processo fechado.");
                return;
            }

            JFrame frameCriarMinuta = CriarMinuta.render(processo);
            frameCriarMinuta.setVisible(true);
            // Ao fechar frame, atualiza tabela
            frameCriarMinuta.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    model.fireTableDataChanged();
                    fluxoTrabalhoModel.fireTableDataChanged();
                }
            });
        });

        botoes[1].addActionListener(e -> {
            if (processo.getFechado()) {
                JOptionPane.showMessageDialog(null, "Não é possível peticionar em um processo fechado.");
                return;
            }

            JFrame frameCriarPeticao = CriarPeticao.render(processo);
            frameCriarPeticao.setVisible(true);
            // Ao fechar frame, atualiza tabela
            frameCriarPeticao.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    model.fireTableDataChanged();
                    fluxoTrabalhoModel.fireTableDataChanged();
                }
            });
        });

        botoes[2].addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Selecione um anexo para visualizar");
                return;
            }

            Anexo anexo = listaAnexos.get(row);
            if (anexo instanceof Minuta) {
                JFrame frameVisualizarMinuta = VisualizarMinuta.render(processo.getNumeroProcesso(), (Minuta) anexo);
                frameVisualizarMinuta.setVisible(true);
            } else {
                JFrame frameVisualizarPeticao = VisualizarPeticao.render(processo.getNumeroProcesso(), (Peticao) anexo);
                frameVisualizarPeticao.setVisible(true);
            }
        });

        botoes[3].addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Selecione uma minuta para editar");
                return;
            }

            if (listaAnexos.get(row) instanceof Peticao) {
                JOptionPane.showMessageDialog(frame, "Não é possível editar uma petição");
                return;
            }

            if (processo.getFechado()) {
                JOptionPane.showMessageDialog(null, "Não é possível editar uma minuta em um processo fechado.");
                return;
            }

            Anexo anexo = listaAnexos.get(row);
            if (anexo instanceof Minuta) {
                if (((Minuta) anexo).getAssinada()) {
                    JOptionPane.showMessageDialog(frame, "Não é possível editar uma minuta assinada");
                    return;
                }

                JFrame frameEditarMinuta = EditarMinuta.render((Minuta) anexo);
                frameEditarMinuta.setVisible(true);
                // Ao fechar frame, atualiza tabela
                frameEditarMinuta.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        model.fireTableDataChanged();
                        fluxoTrabalhoModel.fireTableDataChanged();
                    }
                });
            }
        });

        botoes[4].addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Selecione uma minuta para assinar");
                return;
            }

            if (listaAnexos.get(row) instanceof Peticao) {
                JOptionPane.showMessageDialog(frame, "Não é possível assinar uma petição");
                return;
            }

            Anexo anexo = listaAnexos.get(row);
            if (anexo instanceof Minuta) {
                // Dialog para confirmar se a minuta será assinada ou não
                int dialogResult = JOptionPane.showConfirmDialog(frame, "Deseja assinar a minuta?", "Assinar minuta", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    Juiz juizLogado = (Juiz) (FCToga.getInstance().getUsuarioLogado());
                    ((Minuta) anexo).assinarMinuta(juizLogado.getNomeCompleto(), juizLogado.getComarca());
                    // Se a minuta assinada for do tipo "Sentença", o processo é finalizado
                    if (((Minuta) anexo).getTipoMinuta().equals("Sentença")) {
                        processo.setFechado(true);
                    }
                    FCToga.serializeInstance();
                    JOptionPane.showMessageDialog(frame, "Minuta assinada com sucesso");
                    model.fireTableDataChanged();
                    fluxoTrabalhoModel.fireTableDataChanged();
                }
            }
        });
        //endregion

        // Definindo quais botões serão visíveis com base no usuário logado
        String tipoUsuarioLogado = FCToga.getInstance().getUsuarioLogado().getTipoUsuario();
        // Forma o boolean array conforme o tipo do usuário
        boolean[] botoesVisiveisUsuario = BOTOES_TIPO_USUARIO.getOrDefault(tipoUsuarioLogado, new boolean[]{
                false, false, true, false, false
        });
        List<JButton> botoesVisiveis = new ArrayList<>();
        // Seta os botões como visíveis ou não
        for (int i = 0; i < botoes.length; i++)
            if (botoesVisiveisUsuario[i])
                botoesVisiveis.add(botoes[i]);

        frame.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = botoesVisiveis.size();
        frame.add(panelInformacoesProcesso, c);

        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = botoesVisiveis.size();
        c.gridheight = 2;
        frame.add(scrollPaneTabela, c);

        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        for (JButton botao : botoesVisiveis) {
            frame.add(botao, c);
            c.gridx++;
        }

        frame.pack();
        frame.setMinimumSize(frame.getSize());
        return frame;
    }
}
