package models;

public class Juiz extends Usuario {
    private String comarca;

    // region Boilerplate
    public Juiz(String CPF, String nomeCompleto, String senha, String comarca) {
        super(CPF, nomeCompleto, senha, "Juiz");
        this.comarca = comarca;
    }

    public String getComarca() {
        return comarca;
    }

    public void setComarca(String comarca) {
        this.comarca = comarca;
    }
    // endregion
}
