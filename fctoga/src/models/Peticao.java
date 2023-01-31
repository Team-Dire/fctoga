package models;

public class Peticao extends Anexo {
    private String textoPeticao;
    private Usuario autorPeticao;

    // Construtores, getters e setters
    // region Boilerplate
    public Peticao() {
        super();
    }

    public String getTextoPeticao() {
        return textoPeticao;
    }

    public Peticao setTextoPeticao(String textoPeticao) {
        this.textoPeticao = textoPeticao;
        return this;
    }

    public Usuario getAutorPeticao() {
        return autorPeticao;
    }

    public Peticao setAutorPeticao(Usuario autorPeticao) {
        this.autorPeticao = autorPeticao;
        return this;
    }
    //endregion
}
