package view;

import controllers.FCToga;
import models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuPrincipal {
    private final static String[] botoesLabel = {"Cadastrar usuário", "Fluxo de Trabalho"};
    private final static Runnable[] botoesAcao = {
            () -> {
                JFrame frame = CadastroGUI.render(false);
                frame.setVisible(true);
            },
            () -> {
                JFrame frame = FluxoTrabalho.render();
                frame.setVisible(true);
            }
    };
    // Permissões: administrador vs usuário comum
    private final static Map<String, boolean[]> permissoes = Map.of(
            "Administrador", new boolean[]{true, false}
    );

    public static JFrame render() {
        JFrame frame = new JFrame("FCToga");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criando botões a partir das labels, lambdas e permissões.
        // Adiciona-os ao buttonsBox.
        Usuario usuarioLogado = FCToga.getInstance().getUsuarioLogado();
        String tipoUsuario = usuarioLogado.getTipoUsuario();
        boolean[] permissoesUsuario = permissoes.getOrDefault(tipoUsuario, new boolean[]{false, true});
        // Lista de botões
        List<JButton> botoes = new ArrayList<>();
        for (int i = 0; i < botoesLabel.length; i++) {
            if (permissoesUsuario[i]) {
                JButton botao = new JButton(botoesLabel[i]);
                final Runnable action = botoesAcao[i];
                botao.addActionListener(e -> action.run());
                botoes.add(botao);
            }
        }

        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        frame.add(new JLabel("Bem-vindo, " + usuarioLogado.getNomeCompleto()), c);

        for (JButton botao : botoes) {
            c.gridy++;
            frame.add(botao, c);
        }

        frame.pack();
        frame.setMinimumSize(frame.getSize());
        return frame;
    }
}
