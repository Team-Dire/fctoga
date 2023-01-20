package view.test;

import controllers.ControladorProcesso;
import controllers.FCToga;

import javax.swing.*;
import java.awt.*;

public class CriarProcesso {
    public static JFrame get() {
        JFrame frame = new JFrame("Criar Processo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 400);

        // Label e TextField para cada um dos atributos:
        // CPF_CNPJ_Requerente, nomeRequerente, CPF_CNPJ_Requerido, nomeRequerido
        JLabel CPF_CNPJ_RequerenteLabel = new JLabel("CPF/CNPJ do Requerente");
        JTextField CPF_CNPJ_RequerenteTextField = new JTextField();
        JLabel nomeRequerenteLabel = new JLabel("Nome do Requerente");
        JTextField nomeRequerenteTextField = new JTextField();
        JLabel CPF_CNPJ_RequeridoLabel = new JLabel("CPF/CNPJ do Requerido");
        JTextField CPF_CNPJ_RequeridoTextField = new JTextField();
        JLabel nomeRequeridoLabel = new JLabel("Nome do Requerido");
        JTextField nomeRequeridoTextField = new JTextField();

        // Botão para criar o processo
        JButton criarProcessoButton = new JButton("Criar Processo");
        criarProcessoButton.addActionListener(e -> {
            ControladorProcesso cp = new ControladorProcesso();
            boolean sucesso = false;
            try {
                cp.novoProcesso(
                        CPF_CNPJ_RequerenteTextField.getText(),
                        nomeRequerenteTextField.getText(),
                        CPF_CNPJ_RequeridoTextField.getText(),
                        nomeRequeridoTextField.getText()
                );
                sucesso = true;
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
                sucesso = false;
            }
            if (sucesso) {
                JOptionPane.showMessageDialog(frame, "Processo criado com sucesso!");
                frame.dispose();
            }
        });

        // Layout como BoxLayout vertical
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        // Layout grid (4, 2) para os campos
        JPanel camposPanel = new JPanel(new GridLayout(4, 2));
        camposPanel.add(CPF_CNPJ_RequerenteLabel);
        camposPanel.add(CPF_CNPJ_RequerenteTextField);
        camposPanel.add(nomeRequerenteLabel);
        camposPanel.add(nomeRequerenteTextField);
        camposPanel.add(CPF_CNPJ_RequeridoLabel);
        camposPanel.add(CPF_CNPJ_RequeridoTextField);
        camposPanel.add(nomeRequeridoLabel);
        camposPanel.add(nomeRequeridoTextField);
        frame.getContentPane().add(camposPanel);
        // Botão ocupa uma linha inteira
        frame.getContentPane().add(criarProcessoButton);

        return frame;
    }
}
