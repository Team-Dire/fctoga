package models;

import java.io.Serializable;
import java.util.Date;

public class Anexo implements Serializable {
    private Date dataCriacao;
    private Date dataUltimaModificacao;

    // Construtores, getters e setters
    // region Boilerplate
    public Anexo() {
        this.dataCriacao = new Date();
        this.dataUltimaModificacao = new Date();
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Anexo setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
        return this;
    }

    public Date getDataUltimaModificacao() {
        return dataUltimaModificacao;
    }

    public Anexo setDataUltimaModificacao(Date dataUltimaModificacao) {
        this.dataUltimaModificacao = dataUltimaModificacao;
        return this;
    }
    //endregion
}
