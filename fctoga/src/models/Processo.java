package models;

import controllers.FCToga;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Processo implements Serializable {
    private String nomeRequerente;
    private String CPF_CNPJ_Requerente;
    private String nomeRequerido;
    private String CPF_CNPJ_Requerido;
    private String tipoProceso;
    private Boolean fechado;
    private String numeroProcesso;
    private Date dataCriacao;
    private final ArrayList<Anexo> anexos;
    private final Usuario representante;

    // Construtores, getters e setters
    // region Boilerplate
    public Processo() {
        this.anexos = new ArrayList<>();
        this.representante = FCToga.getInstance().getUsuarioLogado();
    }

    public String getNomeRequerente() {
        return nomeRequerente;
    }

    public Processo setNomeRequerente(String nomeRequerente) {
        this.nomeRequerente = nomeRequerente;
        return this;
    }

    public String getCPF_CNPJ_Requerente() {
        return CPF_CNPJ_Requerente;
    }

    public Processo setCPF_CNPJ_Requerente(String CPF_CNPJ_Requerente) {
        this.CPF_CNPJ_Requerente = CPF_CNPJ_Requerente;
        return this;
    }

    public String getNomeRequerido() {
        return nomeRequerido;
    }

    public Processo setNomeRequerido(String nomeRequerido) {
        this.nomeRequerido = nomeRequerido;
        return this;
    }

    public String getCPF_CNPJ_Requerido() {
        return CPF_CNPJ_Requerido;
    }

    public Processo setCPF_CNPJ_Requerido(String CPF_CNPJ_Requerido) {
        this.CPF_CNPJ_Requerido = CPF_CNPJ_Requerido;
        return this;
    }

    public String getTipoProceso() {
        return tipoProceso;
    }

    public Processo setTipoProceso(String tipoProceso) {
        this.tipoProceso = tipoProceso;
        return this;
    }

    public Boolean getFechado() {
        return fechado;
    }

    public Processo setFechado(Boolean fechado) {
        this.fechado = fechado;
        return this;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public Processo setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
        return this;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Processo setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
        return this;
    }

    public ArrayList<Anexo> getAnexos() {
        return anexos;
    }

    public Usuario getRepresentante() {
        return representante;
    }
    //endregion

    public void adicionarPeticao(String textoPeticao) {
        Anexo peticao = Peticao.criarPeticao(textoPeticao);
        anexos.add(peticao);
    }

    public void adicionarMinuta(String tipoMinuta, String textoMinuta) {
        Minuta minuta = Minuta.criarMinuta(tipoMinuta, textoMinuta);
        anexos.add(minuta);
    }

    public static String numeroProcessoFromData(Date data) {
        return String.format("%02d%02d%02d%02d%02d42%04d0022112",
                data.getDate(),
                data.getMonth() + 1,
                data.getHours(),
                data.getMinutes(),
                data.getSeconds(),
                data.getYear() + 1900);
    }
}