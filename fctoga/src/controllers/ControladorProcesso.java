package controllers;

import models.Processo;
import models.Usuario;

import java.util.Date;

public class ControladorProcesso {
    public static Processo novoProcessoCriminal(String CPF_CNPJ_Requerido, String nomeRequerido, Usuario representanteRequerido) {
        Date dataCriacao = new Date();
        Processo instanciaProcesso = new Processo();
        instanciaProcesso
                .setCPF_CNPJ_Requerente("511740010001")
                .setNomeRequerente("Justiça Pública")
                .setCPF_CNPJ_Requerido(CPF_CNPJ_Requerido)
                .setNomeRequerido(nomeRequerido)
                .setTipoProceso("Criminal")
                .setFechado(false)
                .setDataCriacao(dataCriacao)
                .setNumeroProcesso(Processo.numeroProcessoFromData(dataCriacao))
                .setRepresentanteRequerido(representanteRequerido);
        FCToga.getInstance().processos.add(instanciaProcesso);
        FCToga.serializeInstance();
        return instanciaProcesso;
    }

    public static Processo novoProcessoCivil(String CPF_CNPJ_Requerente, String nomeRequerente, String CPF_CNPJ_Requerido, String nomeRequerido, Usuario representanteRequerido) {
        Date dataCriacao = new Date();
        Processo instanciaProcesso = new Processo();
        instanciaProcesso
                .setCPF_CNPJ_Requerente(CPF_CNPJ_Requerente)
                .setNomeRequerente(nomeRequerente)
                .setCPF_CNPJ_Requerido(CPF_CNPJ_Requerido)
                .setNomeRequerido(nomeRequerido)
                .setTipoProceso("Civil")
                .setFechado(false)
                .setDataCriacao(dataCriacao)
                .setNumeroProcesso(Processo.numeroProcessoFromData(dataCriacao))
                .setRepresentanteRequerido(representanteRequerido);
        FCToga.getInstance().processos.add(instanciaProcesso);
        FCToga.serializeInstance();
        return instanciaProcesso;
    }
}
