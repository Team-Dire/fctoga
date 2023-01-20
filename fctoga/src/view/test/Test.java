package view.test;

import controllers.FCToga;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Test {
    public static void main(String[] args) {
        FCToga fc = FCToga.getInstance();
        String[] botoesLabel = {"Cadastrar", "Login", "Criar Processo", "Listar Processos"};
        Runnable[] botoesAcao = {
                () -> {
                    JFrame telaCadastro = Cadastro.get();
                    telaCadastro.setVisible(true);
                },
                () -> {
                    JFrame telaLogin = Login.get();
                    telaLogin.setVisible(true);
                },
                () -> {
                    JFrame telaCriarProcesso = CriarProcesso.get();
                    telaCriarProcesso.setVisible(true);
                },
                () -> {
                    JFrame telaListarProcessos = ListarProcessos.get();
                    telaListarProcessos.setVisible(true);
                }
        };

        JFrame frame = new JFrame("Teste");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FCToga.serializeInstance();
                frame.dispose();
                System.exit(0);
            }
        });

        // Layout como BoxLayout vertical
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Criando botões a partir das labels e lambdas.
        // Adiciona-os ao frame.
        for (int i = 0; i < botoesLabel.length; i++) {
            JButton botao = new JButton(botoesLabel[i]);
            final Runnable acao = botoesAcao[i];
            botao.addActionListener(e -> acao.run());
            frame.getContentPane().add(botao);
        }

        // Define o tamanho do frame
        frame.setSize(300, 400);

        // Define o frame como visível
        frame.setVisible(true);
    }
}
