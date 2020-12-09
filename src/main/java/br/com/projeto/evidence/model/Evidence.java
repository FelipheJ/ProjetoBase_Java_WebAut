package br.com.projeto.evidence.model;

import br.com.projeto.commons.Utils;

public class Evidence {

    private String cicloCT;
    private String numeroCT;
    private String nomeProjeto;
    private String nomeExecutor;
    private String nomeCasoDeTeste;

    public String getCicloCT() {
        return cicloCT;
    }

    public void setCicloCT(String cicloCT) {
        this.cicloCT = cicloCT;
    }

    public String getNumeroCT() {
        return numeroCT;
    }

    public void setNumeroCT(String numeroCT) {
        this.numeroCT = numeroCT;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getNomeExecutor() {
        return nomeExecutor;
    }

    public void setNomeExecutor(String nomeExecutor) {
        this.nomeExecutor = nomeExecutor;
    }

    public String getNomeCasoDeTeste() {
        return nomeCasoDeTeste;
    }

    public void setNomeCasoDeTeste(String nomeCasoDeTeste) {
        this.nomeCasoDeTeste = nomeCasoDeTeste;
    }

    public String getEvidenceName() {
        return numeroCT + "_" + Utils.DataUtils.obterDataAtual(Utils.DataUtils.DATAHORA);
    }
}
