package models;

import java.util.Date;

public class Minuta extends Anexo {
    private String textoMinuta;
    private String tipoMinuta;
    private Boolean assinada;
    private String nomeJuiz;
    private String comarcaJuiz;

    // Construtores, getters e setters
    // region Boilerplate

    public Minuta(Date dataCriacao, Date dataUltimaModificacao, String textoMinuta, String tipoMinuta, Boolean assinada, String nomeJuiz, String comarcaJuiz) {
        super(dataCriacao, dataUltimaModificacao);
        this.textoMinuta = textoMinuta;
        this.tipoMinuta = tipoMinuta;
        this.assinada = assinada;
        this.nomeJuiz = nomeJuiz;
        this.comarcaJuiz = comarcaJuiz;
    }

    public String getTextoMinuta() {
        return textoMinuta;
    }

    public void setTextoMinuta(String textoMinuta) {
        this.textoMinuta = textoMinuta;
    }

    public String getTipoMinuta() {
        return tipoMinuta;
    }

    public void setTipoMinuta(String tipoMinuta) {
        this.tipoMinuta = tipoMinuta;
    }

    public Boolean getAssinada() {
        return assinada;
    }

    public void setAssinada(Boolean assinada) {
        this.assinada = assinada;
    }

    public String getNomeJuiz() {
        return nomeJuiz;
    }

    public void setNomeJuiz(String nomeJuiz) {
        this.nomeJuiz = nomeJuiz;
    }

    public String getComarcaJuiz() {
        return comarcaJuiz;
    }

    public void setComarcaJuiz(String comarcaJuiz) {
        this.comarcaJuiz = comarcaJuiz;
    }
    //endregion

    public Boolean alterarMinuta(String textoMinuta) {
        if (this.assinada) {
            return false;
        }
        this.textoMinuta = textoMinuta;
        this.setDataUltimaModificacao(new Date());
        return true;
    }

    public static Minuta criarMinuta(String tipoMinuta, String textoMinuta) {
        return new Minuta(new Date(), new Date(), textoMinuta, tipoMinuta, false, "", "");
    }

    public String assinarMinuta(String nomeJuiz, String comarcaJuiz) {
        this.assinada = true;
        this.nomeJuiz = nomeJuiz;
        this.comarcaJuiz = comarcaJuiz;
        return tipoMinuta;
    }
}
