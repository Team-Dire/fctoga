package models;

import java.util.Date;

public class Peticao extends Anexo {
    private String textoPeticao;

    // Construtores, getters e setters
    // region Boilerplate
    public Peticao(Date dataCriacao, Date dataUltimaModificacao, String textoPeticao) {
        super(dataCriacao, dataUltimaModificacao);
        this.textoPeticao = textoPeticao;
    }

    public String getTextoPeticao() {
        return textoPeticao;
    }

    public void setTextoPeticao(String textoPeticao) {
        this.textoPeticao = textoPeticao;
    }
    //endregion

    static public Peticao criarPeticao(String textoPeticao) {
        Peticao peticao = new Peticao(new Date(), new Date(), textoPeticao);
        return peticao;
    }
}
