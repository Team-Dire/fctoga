package view;

import controllers.FCToga;
import models.Usuario;
import view.processos.ListarProcessos;

import javax.swing.*;
import java.util.Map;

public class MenuPrincipal {
    private final static String[] botoesLabel = {"Cadastrar usuário", "Listar Processos"};
    private final static Runnable[] botoesAcao = {
            () -> {
                JFrame frame = CadastroGUI.get();
                frame.setVisible(true);
            },
            () -> {
                JFrame frame = ListarProcessos.render();
                frame.setVisible(true);
            }
    };
    // Permissões: administrador vs usuário comum
    private final static Map<String, boolean[]> permissoes = Map.of(
            "Administrador", new boolean[]{true, true}
    );

    public static JFrame render() {
        JFrame frame = new JFrame("FCToga");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Box vertical para os botões
        Box buttonsBox = Box.createVerticalBox();

        // Criando botões a partir das labels, lambdas e permissões.
        // Adiciona-os ao buttonsBox.
        Usuario usuarioLogado = FCToga.getInstance().getUsuarioLogado();
        String tipoUsuario = usuarioLogado.getTipoUsuario();
        boolean[] permissoesUsuario = permissoes.getOrDefault(tipoUsuario, new boolean[]{false, true});
        for (int i = 0; i < botoesLabel.length; i++) {
            if (permissoesUsuario[i]) {
                JButton botao = new JButton(botoesLabel[i]);
                final Runnable action = botoesAcao[i];
                botao.addActionListener(e -> action.run());
                buttonsBox.add(botao);
            }
        }

        // Define o tamanho do frame
        frame.setSize(300, 400);
        frame.getContentPane().add(buttonsBox);

        // Define o frame como visível
        return frame;
    }
}
