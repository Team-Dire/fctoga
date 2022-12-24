package models;

public class Advogado extends Usuario {
    private String numeroOAB;
    private String estadoOAB;

    // region Boilerplate
    public Advogado(String CPF, String nomeCompleto, String senha, String numeroOAB, String estadoOAB) {
        super(CPF, nomeCompleto, senha);
        this.numeroOAB = numeroOAB;
        this.estadoOAB = estadoOAB;
    }

    public String getNumeroOAB() {
        return numeroOAB;
    }

    public void setNumeroOAB(String numeroOAB) {
        this.numeroOAB = numeroOAB;
    }

    public String getEstadoOAB() {
        return estadoOAB;
    }

    public void setEstadoOAB(String estadoOAB) {
        this.estadoOAB = estadoOAB;
    }
    // endregion
}
