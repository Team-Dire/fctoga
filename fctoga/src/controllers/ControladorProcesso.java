package controllers;

import models.Anexo;
import models.Minuta;
import models.Processo;
import utils.NotImplemented;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ControladorProcesso {
    private final FCToga fctoga;
    private Processo processo;
    private Anexo anexo;

    // Injeção de dependência.
    public ControladorProcesso(FCToga fctoga) {
        this.fctoga = fctoga;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public void adicionarMinuta(String tipoMinuta, String textoMinuta) {
        this.processo.adicionarMinuta(tipoMinuta, textoMinuta);
    }

    public void adicionarPeticao(String textoPeticao) {
        this.processo.adicionarPeticao(textoPeticao);
    }

    public void novoProcesso(String CPF_CNPJ_Requerente, String nomeRequerente, String CPF_CNPJ_Requerido, String nomeRequerido) {
        Processo instanciaProcesso = Processo.criarProcesso(CPF_CNPJ_Requerente, nomeRequerente, CPF_CNPJ_Requerido, nomeRequerido);
        this.fctoga.processos.add(instanciaProcesso);
    }

    public ArrayList<Anexo> visualizarHistoricoProcesso(String numeroProcesso) {
        Processo instanciaProcesso = this.fctoga.processos.stream().filter(processo -> processo.getNumeroProcesso().equals(numeroProcesso)).findFirst().orElseThrow(() -> new NoSuchElementException("Processo não encontrado."));
        return instanciaProcesso.visualizarHistoricoProcesso();
    }

    @NotImplemented
    public Boolean alterarMinuta(String textoMinuta) {
        return null;
    }

    @NotImplemented
    public Anexo visualizarAnexoProcesso() {
        return null;
    }

    public void assinarMinuta(String nomeJuiz, String comarcaJuiz) {
        try {
            Minuta minutaInstancia = ((Minuta) (this.anexo));
            minutaInstancia.assinarMinuta(nomeJuiz, comarcaJuiz);
        }
        catch (ClassCastException eNaoMinuta) {
            throw new ClassCastException("Anexo não é uma minuta.");
        }
    }
}
