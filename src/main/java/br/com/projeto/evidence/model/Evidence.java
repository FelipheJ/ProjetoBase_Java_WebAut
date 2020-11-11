package br.com.projeto.evidence.model;

public class Evidence {

    private String nomeProjeto;
    private String nomeExecutor;
    private String nomeCasoDeTeste;

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
}
