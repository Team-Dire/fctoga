package view.processos;

import controllers.FCToga;
import models.Processo;
import view.anexos.ListarAnexos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ListarProcessos {
    private final static String[] COLUNAS = {"Nome do Requerente", "CPF/CNPJ do Requerente", "Nome do Requerido", "CPF/CNPJ do Requerido", "Data de Abertura", "Status"};

    private final static Map<String, boolean[]> PERMISSOES_BOTOES = Map.of(
            "Promotor", new boolean[]{true, true},
            "Advogado", new boolean[]{true, true}
    );

    public static JFrame render() {
        JFrame frame = new JFrame("Lista de Processos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ==================== Tabela ====================
        JTable table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        List<Processo> processos = FCToga.getInstance().processos;
        // Listar os processos na JTable usando um DefaultTableModel
        // Responsável por recuperar os processos diretamente da lista de processos
        DefaultTableModel modelTabelaProcessos = new DefaultTableModel(COLUNAS, 0) {
            // Proíbe edição de células
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public int getRowCount() {
                return processos.size();
            }

            @Override
            public Object getValueAt(int row, int column) {
                Processo processo = processos.get(row);
                return switch (column) {
                    case 0 -> processo.getNomeRequerente();
                    case 1 -> processo.getCPF_CNPJ_Requerente();
                    case 2 -> processo.getNomeRequerido();
                    case 3 -> processo.getCPF_CNPJ_Requerido();
                    case 4 -> processo.getDataCriacao();
                    case 5 -> processo.getFechado() ? "Fechado" : "Aberto";
                    default -> null;
                };
            }
        };
        table.setModel(modelTabelaProcessos);
        JScrollPane scrollPaneTabelaProcessos = new JScrollPane(table);
        // ==================== Tabela ====================

        // ==================== Botões =====================
        JButton[] btns = new JButton[2];
        // Criar processo
        btns[0] = new JButton("Criar novo processo");
        btns[0].addActionListener(e -> {
            JFrame frameCriarProcesso = CriarProcesso.render();
            frameCriarProcesso.setVisible(true);
            // Atualizar tabela ao fechar a janela de criar processo
            frameCriarProcesso.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    modelTabelaProcessos.fireTableDataChanged();
                }
            });
        });

        // Listar anexos
        btns[1] = new JButton("Visualizar histórico");
        btns[1].addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Selecione um processo para listar os anexos");
                return;
            }
            Processo p = processos.get(selectedRow);
            JFrame anexosFrame = ListarAnexos.render(p);
            anexosFrame.setVisible(true);
        });

        String tipoUsuarioLogado = FCToga.getInstance().getUsuarioLogado().getTipoUsuario();
        boolean[] botoesPermitidos = PERMISSOES_BOTOES.getOrDefault(tipoUsuarioLogado, new boolean[]{false, true});
        List<JButton> botoesVisiveis = new ArrayList<>();
        for (int i = 0; i < btns.length; i++) {
            if (botoesPermitidos[i])
                botoesVisiveis.add(btns[i]);
        }
        // ==================== Botões =====================

        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        // Número de colunas depende do número de botões
        c.gridwidth = botoesVisiveis.size();
        c.gridx = 0; c.gridy = 0; frame.add(scrollPaneTabelaProcessos, c);
        c.gridwidth = 1;
        for (int i = 0; i < botoesVisiveis.size(); i++) {
            c.gridx = i; c.gridy = 1; frame.add(botoesVisiveis.get(i), c);
        }
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        return frame;
    }
}
