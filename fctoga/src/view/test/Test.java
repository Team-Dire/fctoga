package view.test;

import controllers.FCToga;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Test {
    public static void main(String[] args) {
        FCToga fc = FCToga.loadSerializedInstance();
        String[] botoesLabel = {"Cadastrar", "Login", "Criar Processo", "Listar Processos"};
        Runnable[] botoesAcao = {
                () -> {
                    JFrame telaCadastro = Cadastro.get(fc);
                    telaCadastro.setVisible(true);
                },
                () -> {
                    JFrame telaLogin = Login.get(fc);
                    telaLogin.setVisible(true);
                },
                () -> {
                    JFrame telaCriarProcesso = CriarProcesso.get(fc);
                    telaCriarProcesso.setVisible(true);
                },
                () -> {
                    JFrame telaListarProcessos = ListarProcessos.get(fc);
                    telaListarProcessos.setVisible(true);
                }
        };

        JFrame frame = new JFrame("Teste");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FCToga.serializeInstance(fc);
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
            int finalI = i;
            botao.addActionListener(e -> botoesAcao[finalI].run());
            frame.getContentPane().add(botao);
        }

        // Define o tamanho do frame
        frame.setSize(300, 400);

        // Define o frame como visível
        frame.setVisible(true);
    }
}
