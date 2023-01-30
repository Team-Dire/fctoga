package models;

import java.util.Date;

public class Minuta extends Anexo {
    private String textoMinuta;
    private String tipoMinuta;
    private Boolean assinada;
    private String nomeJuiz;
    private String comarcaJuiz;
    private Usuario autorMinuta;

    // Construtores, getters e setters
    // region Boilerplate

    public Minuta() {
        super();
        this.assinada = false;
    }

    public String getTextoMinuta() {
        return textoMinuta;
    }

    public Minuta setTextoMinuta(String textoMinuta) {
        this.textoMinuta = textoMinuta;
        return this;
    }

    public String getTipoMinuta() {
        return tipoMinuta;
    }

    public Minuta setTipoMinuta(String tipoMinuta) {
        this.tipoMinuta = tipoMinuta;
        return this;
    }

    public Boolean getAssinada() {
        return assinada;
    }

    public String getNomeJuiz() {
        return nomeJuiz;
    }

    public Minuta setNomeJuiz(String nomeJuiz) {
        this.nomeJuiz = nomeJuiz;
        return this;
    }

    public String getComarcaJuiz() {
        return comarcaJuiz;
    }

    public Minuta setComarcaJuiz(String comarcaJuiz) {
        this.comarcaJuiz = comarcaJuiz;
        return this;
    }

    public Usuario getAutorMinuta() {
        return autorMinuta;
    }

    public Minuta setAutorMinuta(Usuario autorMinuta) {
        this.autorMinuta = autorMinuta;
        return this;
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

    public String assinarMinuta(String nomeJuiz, String comarcaJuiz) {
        this.assinada = true;
        this.nomeJuiz = nomeJuiz;
        this.comarcaJuiz = comarcaJuiz;
        return tipoMinuta;
    }
}
