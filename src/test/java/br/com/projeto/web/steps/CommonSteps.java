package br.com.projeto.web.steps;

import io.cucumber.java.pt.*;
import io.cucumber.datatable.DataTable;
import br.com.projeto.web.functionalities.evidence.Parameters;
import br.com.projeto.web.functionalities.CommonFuncionalities;

import static org.junit.Assert.*;

public class CommonSteps {

    private final Parameters parameters = new Parameters();
    private final CommonFuncionalities commonFunc = new CommonFuncionalities();

    @Dado("que acesso o site {string}")
    public void queAcessoOSite(String link) {
        commonFunc.acessarSite(link);
    }

    @E("estou executando o teste")
    public void estouExecutandoOTeste(DataTable parametros) {
        parameters.estouExecutandoOTeste(parametros);
    }

    @E("insiro no campo {string} o valor {string}")
    public void insiroNoCampoOValor(String campo, String valor) {
        commonFunc.inserirValorNoCampo(valor, campo);
    }

    @Quando("aperto a tecla {string}")
    public void apertoATecla(String tecla) {
        commonFunc.apertarTecla(tecla);
    }

    @Então("visualizo o site {string} na lista de resultados")
    public void visualizoOSiteNaListaDeResultados(String site) {
        assertTrue(commonFunc.visualizarSite(site));
    }
}
