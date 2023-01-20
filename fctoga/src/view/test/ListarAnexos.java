package view.test;

import models.Anexo;
import models.Minuta;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ListarAnexos {
    public static JFrame get(ArrayList<Anexo> anexos) {
        JFrame frame = new JFrame("Listar Anexos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 900);

        // ========== Tabela ==========
        JTable table = new JTable();
        String[] colunas = {"Data de criação", "Data de última modificação", "Tipo", "Assinada"};

        AbstractTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public int getRowCount() {
                return anexos.size();
            }

            @Override
            public int getColumnCount() {
                return colunas.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return switch (columnIndex) {
                    case 0 -> anexos.get(rowIndex).getDataCriacao();
                    case 1 -> anexos.get(rowIndex).getDataUltimaModificacao();
                    case 2 -> anexos.get(rowIndex) instanceof Minuta ? "Minuta" : "Petição";
                    case 3 ->
                            anexos.get(rowIndex) instanceof Minuta ? (((Minuta) anexos.get(rowIndex)).getAssinada() ? "Assinada" : "Não assinada") : "N/A";
                    default -> null;
                };
            }
        };

        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        // ========== Tabela ==========

        // Botão de editar
        JButton editar = new JButton("Editar");
        editar.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Selecione um anexo para editar");
                return;
            }
            Anexo anexo = anexos.get(row);
            if (anexo instanceof Minuta) {
                JFrame frameAlterarMinuta = AlterarMinuta.get((Minuta) (anexo));
                frameAlterarMinuta.setVisible(true);
                // Event listener para atualizar a tabela quando a minuta for alterada
                frameAlterarMinuta.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        model.fireTableRowsUpdated(row, row);
                    }
                });
            }
        });

        // Botão de assinar (minuta)
        JButton assinar = new JButton("Assinar");
        assinar.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Selecione uma minuta para assinar");
                return;
            }
            Anexo anexo = anexos.get(row);
            if (anexo instanceof Minuta) {
                // Dialog para confirmar assinatura
                int dialogResult = JOptionPane.showConfirmDialog(frame, "Tem certeza que deseja assinar esta minuta?", "Assinar minuta", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    // TODO como recuperar usuário atual? Provavelmente irá afetar a lógica inteira
                    ((Minuta) anexo).assinarMinuta("NOME_PADRAO_JUIZ", "NOME_PADRAO_VARA");
                    model.fireTableRowsUpdated(row, row);
                }
            }
        });


        // BoxLayout horizontal para os botões
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(editar);
        buttonsPanel.add(assinar);

        // BoxLayout vertical para o Frame
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(buttonsPanel);
        return frame;
    }
}
