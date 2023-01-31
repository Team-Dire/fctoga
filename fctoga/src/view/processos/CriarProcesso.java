package view.processos;

import controllers.ControladorProcesso;
import controllers.FCToga;
import models.Processo;
import models.Usuario;
import view.utils.CPFCNPJInputVerifier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CriarProcesso {
    public static JFrame render(DefaultTableModel fluxoDeTrabalhoModel) {
        JFrame frame = new JFrame("Criar Processo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(400, 600);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;

        String tipoUsuarioLogado = FCToga.getInstance().getUsuarioLogado().getTipoUsuario();

        // ===== CAMPOS DE ENTRADA =====
        // Todos os JTextField são guardados em uma lista para facilitar a leitura dos dados
        // e verificar se todos os campos foram preenchidos
        List<JTextComponent> textFields = new ArrayList<>();

        JLabel labelCPFCNPJRequerente = new JLabel("CPF/CNPJ do Requerente");
        JTextField inputCPFCNPJRequerente = new JTextField();
        inputCPFCNPJRequerente.setInputVerifier(new CPFCNPJInputVerifier());
        JLabel labelNomeRequerente = new JLabel("Nome do Requerente");
        JTextField inputNomeRequerente = new JTextField();
        // Só é obrigado se for Advogado
        if (tipoUsuarioLogado.equals("Advogado")) {
            frame.add(labelCPFCNPJRequerente, c);
            c.gridx++;
            frame.add(inputCPFCNPJRequerente, c);
            textFields.add(inputCPFCNPJRequerente);
            c.gridy++;
            c.gridx = 0;
            frame.add(labelNomeRequerente, c);
            c.gridx++;
            frame.add(inputNomeRequerente, c);
            textFields.add(inputNomeRequerente);
            c.gridy++;
            c.gridx = 0;
        }

        JLabel labelCPFCNPJRequerido = new JLabel("CPF/CNPJ do Requerido");
        JTextField fieldCPFCNPJRequerido = new JTextField();
        fieldCPFCNPJRequerido.setInputVerifier(new CPFCNPJInputVerifier());
        frame.add(labelCPFCNPJRequerido, c);
        c.gridx++;
        frame.add(fieldCPFCNPJRequerido, c);
        textFields.add(fieldCPFCNPJRequerido);
        c.gridy++;
        c.gridx = 0;

        JLabel labelNomeRequerido = new JLabel("Nome do Requerido");
        JTextField fieldNomeRequerido = new JTextField();
        frame.add(labelNomeRequerido, c);
        c.gridx++;
        frame.add(fieldNomeRequerido, c);
        textFields.add(fieldNomeRequerido);
        c.gridy++;
        c.gridx = 0;

        // Representante do Requerido
        JLabel labelRepresentanteRequerido = new JLabel("Representante do Requerido");
        JComboBox<Usuario> fieldRepresentanteRequerido = new JComboBox<>();
        // Todos os usuários do tipo "Advogado" com exceção do usuário logado
        FCToga.getInstance().getUsuarios().stream().filter(usuario -> usuario.getTipoUsuario().equals("Advogado") && !usuario.equals(FCToga.getInstance().getUsuarioLogado())).forEach(fieldRepresentanteRequerido::addItem);
        // No combobox, aparece o nome do advogado adicionado
        // Se não houver outros advogados além do usuário logado, o ComboBox será vazio
        if (fieldRepresentanteRequerido.getItemCount() != 0)
            fieldRepresentanteRequerido.setRenderer((list, value, index, isSelected, cellHasFocus) -> new JLabel(value.getNomeCompleto()));

        frame.add(labelRepresentanteRequerido, c);
        c.gridx++;
        frame.add(fieldRepresentanteRequerido, c);
        c.gridy++;
        c.gridx = 0;

        // Petição inicial
        JLabel labelPeticaoInicial = new JLabel("Petição Inicial");
        JTextArea fieldPeticaoInicial = new JTextArea(20, 20);
        JScrollPane scrollPeticaoInicial = new JScrollPane(fieldPeticaoInicial);
        scrollPeticaoInicial.setMinimumSize(scrollPeticaoInicial.getPreferredSize());
        c.gridheight = 2;
        frame.add(labelPeticaoInicial, c);
        c.gridx++;
        frame.add(scrollPeticaoInicial, c);
        textFields.add(fieldPeticaoInicial);
        c.gridy += 2;
        c.gridx = 0;
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

                // Verifica se há representante do requerido
                if (fieldRepresentanteRequerido.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(frame, "Selecione um representante do requerido", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verifica se usuário é advogado ou promotor
                // Advogado abre processo civil, promotor abre processo criminal
                if (tipoUsuarioLogado.equals("Advogado")) {
                    Processo processoCriado = ControladorProcesso.novoProcessoCivil(
                            inputCPFCNPJRequerente.getText(),
                            inputNomeRequerente.getText(),
                            fieldCPFCNPJRequerido.getText(),
                            fieldNomeRequerido.getText(),
                            (Usuario) fieldRepresentanteRequerido.getSelectedItem()
                    );
                    processoCriado.adicionarPeticao(fieldPeticaoInicial.getText());
                    FCToga.serializeInstance();
                } else if (tipoUsuarioLogado.equals("Promotor")) {
                    Processo processoCriado = ControladorProcesso.novoProcessoCriminal(
                            fieldCPFCNPJRequerido.getText(),
                            fieldNomeRequerido.getText(),
                            (Usuario) fieldRepresentanteRequerido.getSelectedItem()
                    );
                    processoCriado.adicionarPeticao(fieldPeticaoInicial.getText());
                    FCToga.serializeInstance();
                } else {
                    throw new Exception("Usuário não é advogado nem promotor");
                }

                sucesso = true;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
                sucesso = false;
            }
            if (sucesso) {
                fluxoDeTrabalhoModel.fireTableDataChanged();
                JOptionPane.showMessageDialog(frame, "Processo criado com sucesso!");
                frame.dispose();
            }
        });
        c.gridheight = 1;
        c.gridwidth = 2;
        frame.add(criarProcessoButton, c);
        // ===== BOTÃO DE CRIAR PROCESSO =====

        return frame;
    }
}
