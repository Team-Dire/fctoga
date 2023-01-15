package models;

import java.io.Serializable;
import java.util.Date;

public class Anexo implements Serializable {
    private Date dataCriacao;
    private Date dataUltimaModificacao;

    // Construtores, getters e setters
    // region Boilerplate
    public Anexo(Date dataCriacao, Date dataUltimaModificacao) {
        this.dataCriacao = dataCriacao;
        this.dataUltimaModificacao = dataUltimaModificacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataUltimaModificacao() {
        return dataUltimaModificacao;
    }

    public void setDataUltimaModificacao(Date dataUltimaModificacao) {
        this.dataUltimaModificacao = dataUltimaModificacao;
    }

    //endregion

    public Anexo visualizarAnexo() {
        return null;
    }
}
