package view.test;

import controllers.FCToga;
import models.Processo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListarProcessos {
    public static JFrame get(FCToga fc) {
        JFrame frame = new JFrame("Lista de Processos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 900);
        JTable table = new JTable();
        String[] colunas = {"Nome do Requerente", "CPF do Requerente", "Nome do Requerido", "CPF do Requerido", "Data de Abertura", "Status"};
        List<Processo> processos = fc.processos;
        // Listar os processos na JTable
        Object[][] data = processos.stream().map(p -> {
            Object[] row = new Object[6];
            row[0] = p.getNomeRequerente();
            row[1] = p.getCPF_CNPJ_Requerente();
            row[2] = p.getNomeRequerido();
            row[3] = p.getCPF_CNPJ_Requerido();
            row[4] = p.getDataCriacao();
            row[5] = p.getFechado() ? "Fechado" : "Aberto";
            return row;
        }).toArray(Object[][]::new);
        table.setModel(new DefaultTableModel(data, colunas));
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane);
        return frame;
    }
}
