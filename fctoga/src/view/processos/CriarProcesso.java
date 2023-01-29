package view.processos;

import controllers.ControladorProcesso;
import controllers.FCToga;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import models.Processo;
import view.utils.CPFCNPJInputVerifier;

public class CriarProcesso {
    public static JFrame render() {
        JFrame frame = new JFrame("Criar Processo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 400);

        String tipoUsuarioLogado = FCToga.getInstance().getUsuarioLogado().getTipoUsuario();

        // ===== CAMPOS DE ENTRADA =====
        // Todos os JTextField são guardados em uma lista para facilitar a leitura dos dados
        // e verificar se todos os campos foram preenchidos
        List<JTextComponent> textFields = new ArrayList<>();

        JLabel labelCPFCNPJRequerente = new JLabel("CPF/CNPJ do Requerente");
        JTextField inputCPFCNPJRequerente = new JTextField();
        inputCPFCNPJRequerente.setInputVerifier(new CPFCNPJInputVerifier());
        // Só é obrigatório se for advogado
        if (tipoUsuarioLogado.equals("advogado"))
            textFields.add(inputCPFCNPJRequerente);
        Box boxCPFCNPJRequerente = Box.createHorizontalBox();
        boxCPFCNPJRequerente.add(labelCPFCNPJRequerente);
        boxCPFCNPJRequerente.add(Box.createHorizontalStrut(10));
        boxCPFCNPJRequerente.add(inputCPFCNPJRequerente);

        JLabel labelNomeRequerente = new JLabel("Nome do Requerente");
        JTextField inputNomeRequerente = new JTextField();
        if (tipoUsuarioLogado.equals("advogado"))
            textFields.add(inputNomeRequerente);
        Box boxNomeRequerente = Box.createHorizontalBox();
        boxNomeRequerente.add(labelNomeRequerente);
        boxNomeRequerente.add(Box.createHorizontalStrut(10));
        boxNomeRequerente.add(inputNomeRequerente);

        JLabel labelCPFCNPJRequerido = new JLabel("CPF/CNPJ do Requerido");
        JTextField fieldCPFCNPJRequerido = new JTextField();
        fieldCPFCNPJRequerido.setInputVerifier(new CPFCNPJInputVerifier());
        textFields.add(fieldCPFCNPJRequerido);
        Box boxCPFCNPJRequerido = Box.createHorizontalBox();
        boxCPFCNPJRequerido.add(labelCPFCNPJRequerido);
        boxCPFCNPJRequerido.add(Box.createHorizontalStrut(10));
        boxCPFCNPJRequerido.add(fieldCPFCNPJRequerido);

        JLabel labelNomeRequerido = new JLabel("Nome do Requerido");
        JTextField fieldNomeRequerido = new JTextField();
        textFields.add(fieldNomeRequerido);
        Box boxNomeRequerido = Box.createHorizontalBox();
        boxNomeRequerido.add(labelNomeRequerido);
        boxNomeRequerido.add(Box.createHorizontalStrut(10));
        boxNomeRequerido.add(fieldNomeRequerido);

        // Petição inicial
        JLabel labelPeticaoInicial = new JLabel("Petição Inicial");
        JTextArea fieldPeticaoInicial = new JTextArea(100, 100);
        textFields.add(fieldPeticaoInicial);
        JScrollPane scrollPeticaoInicial = new JScrollPane(fieldPeticaoInicial);
        Box boxPeticaoInicial = Box.createHorizontalBox();
        boxPeticaoInicial.add(labelPeticaoInicial);
        boxPeticaoInicial.add(Box.createHorizontalStrut(10));
        boxPeticaoInicial.add(scrollPeticaoInicial);
        // ===== CAMPOS DE ENTRADA =====

        // ===== BOTÃO DE CRIAR PROCESSO =====
        JButton criarProcessoButton = new JButton("Criar Processo");
        criarProcessoButton.addActionListener(e -> {
            boolean sucesso;
            try {
                for (JTextComponent field : textFields) {
                    if (field.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                // Verifica se usuário é advogado ou promotor
                // Advogado abre processo civil, promotor abre processo criminal
                if (tipoUsuarioLogado.equals("Advogado")) {
                    Processo processoCriado = ControladorProcesso.novoProcessoCivil(
                            inputCPFCNPJRequerente.getText(),
                            inputNomeRequerente.getText(),
                            fieldCPFCNPJRequerido.getText(),
                            fieldNomeRequerido.getText()
                    );
                    processoCriado.adicionarPeticao(fieldPeticaoInicial.getText());
                }
                else if (tipoUsuarioLogado.equals("Promotor")) {
                    Processo processoCriado = ControladorProcesso.novoProcessoCriminal(
                            fieldCPFCNPJRequerido.getText(),
                            fieldNomeRequerido.getText()
                    );
                    processoCriado.adicionarPeticao(fieldPeticaoInicial.getText());
                }
                else {
                    throw new Exception("Usuário não é advogado nem promotor");
                }

                sucesso = true;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
                sucesso = false;
            }
            if (sucesso) {
                JOptionPane.showMessageDialog(frame, "Processo criado com sucesso!");
                frame.dispose();
            }
        });
        Box criarProcessoButtonBox = Box.createHorizontalBox();
        criarProcessoButtonBox.add(criarProcessoButton);
        // ===== BOTÃO DE CRIAR PROCESSO =====

        // BoxLayout vertical
        Box frameBox = Box.createVerticalBox();
        // CPF/CNPJ de Requerente só é exibido para advogados
        if (tipoUsuarioLogado.equals("Advogado")) {
            frameBox.add(boxCPFCNPJRequerente);
            frameBox.add(Box.createVerticalStrut(10));
            frameBox.add(boxNomeRequerente);
            frameBox.add(Box.createVerticalStrut(10));
        }
        frameBox.add(boxCPFCNPJRequerido);
        frameBox.add(Box.createVerticalStrut(10));
        frameBox.add(boxNomeRequerido);
        frameBox.add(Box.createVerticalStrut(10));
        frameBox.add(boxPeticaoInicial);
        frameBox.add(Box.createVerticalStrut(10));
        frameBox.add(criarProcessoButtonBox);

        frame.getContentPane().add(frameBox, BorderLayout.CENTER);
        return frame;
    }
}
