package controllers;

import models.Processo;

public class ControladorProcesso {
    public static void novoProcesso(String CPF_CNPJ_Requerente, String nomeRequerente, String CPF_CNPJ_Requerido, String nomeRequerido) {
        Processo instanciaProcesso = Processo.criarProcesso(CPF_CNPJ_Requerente, nomeRequerente, CPF_CNPJ_Requerido, nomeRequerido);
        FCToga.getInstance().processos.add(instanciaProcesso);
        FCToga.serializeInstance();
    }
}
