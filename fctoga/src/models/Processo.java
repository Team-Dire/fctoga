package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Processo {
    private String nomeRequerente;
    private String CPF_CNPJ_Requerente;
    private String nomeRequerido;
    private String CPF_CNPJ_Requerido;
    private String tipoProceso;
    private Boolean fechado;
    private String numeroProcesso;
    private Date dataCriacao;
    private final ArrayList<Anexo> anexos;

    // Construtores, getters e setters
    // region Boilerplate
    public Processo(String nomeRequerente, String CPF_CNPJ_Requerente, String nomeRequerido, String CPF_CNPJ_Requerido, String tipoProceso, Boolean fechado, String numeroProcesso, Date dataCriacao) {
        this.nomeRequerente = nomeRequerente;
        this.CPF_CNPJ_Requerente = CPF_CNPJ_Requerente;
        this.nomeRequerido = nomeRequerido;
        this.CPF_CNPJ_Requerido = CPF_CNPJ_Requerido;
        this.tipoProceso = tipoProceso;
        this.fechado = fechado;
        this.numeroProcesso = numeroProcesso;
        this.dataCriacao = dataCriacao;
        this.anexos = new ArrayList<>();
    }

    public String getNomeRequerente() {
        return nomeRequerente;
    }

    public void setNomeRequerente(String nomeRequerente) {
        this.nomeRequerente = nomeRequerente;
    }

    public String getCPF_CNPJ_Requerente() {
        return CPF_CNPJ_Requerente;
    }

    public void setCPF_CNPJ_Requerente(String CPF_CNPJ_Requerente) {
        this.CPF_CNPJ_Requerente = CPF_CNPJ_Requerente;
    }

    public String getNomeRequerido() {
        return nomeRequerido;
    }

    public void setNomeRequerido(String nomeRequerido) {
        this.nomeRequerido = nomeRequerido;
    }

    public String getCPF_CNPJ_Requerido() {
        return CPF_CNPJ_Requerido;
    }

    public void setCPF_CNPJ_Requerido(String CPF_CNPJ_Requerido) {
        this.CPF_CNPJ_Requerido = CPF_CNPJ_Requerido;
    }

    public String getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(String tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public Boolean getFechado() {
        return fechado;
    }

    public void setFechado(Boolean fechado) {
        this.fechado = fechado;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    //endregion

    public ArrayList<Anexo> visualizarHistoricoProcesso() {
        return anexos;
    }

    public Boolean alterarMinuta(String textoMinuta) {
        Minuta ultimaMinutaDoProcesso = (Minuta) (anexos
                .stream()
                .filter(anexo -> anexo instanceof Minuta)
                .sorted(Collections.reverseOrder())
                .findFirst()
                .orElse(null));
        if (ultimaMinutaDoProcesso != null) {
            return ultimaMinutaDoProcesso.alterarMinuta(textoMinuta);
        }
        return false;
    }

    public Processo criarProcesso(String CPF_CNPJ_Requerente, String nomeRequerente, String CPF_CNPJ_Requerido, String nomeRequerido) {
        return new Processo(nomeRequerente, CPF_CNPJ_Requerente, nomeRequerido, CPF_CNPJ_Requerido, "Processo", false, "00000000000000000000", new Date());
    }

    public void adicionarPeticao(String textoPeticao) {
        Peticao peticao = Peticao.criarPeticao(textoPeticao);
        anexos.add(peticao);
    }

    public void adicionarMinuta(String tipoMinuta, String textoMinuta) {
        Minuta minuta = Minuta.criarMinuta(tipoMinuta, textoMinuta);
        anexos.add(minuta);
    }

    public void fecharProcesso() {
        this.fechado = true;
    }

    public void assinarMinuta(String nomeJuiz, String comarcaJuiz) {
        Minuta ultimaMinutaDoProcesso = (Minuta) (anexos
                .stream()
                .filter(anexo -> anexo instanceof Minuta)
                .sorted(Collections.reverseOrder())
                .findFirst()
                .orElse(null));
        if (ultimaMinutaDoProcesso != null) {
            String tipoMinuta = ultimaMinutaDoProcesso.assinarMinuta(nomeJuiz, comarcaJuiz);
            if (tipoMinuta.equals("Sentença")) {
                this.fechado = true;
            }
        }
    }

}
