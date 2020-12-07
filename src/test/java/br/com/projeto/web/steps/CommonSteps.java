package br.com.projeto.web.steps;

import org.junit.Assert;
import io.cucumber.java.pt.*;
import java.lang.reflect.Method;
import io.cucumber.datatable.DataTable;
import br.com.projeto.commons.StepExecutor;
import br.com.projeto.web.functionalities.evidence.Parameters;
import br.com.projeto.web.functionalities.CommonFuncionalities;

public class CommonSteps {

    private Parameters parameters = new Parameters();
    private StepExecutor stepExecutor = new StepExecutor();
    private CommonFuncionalities commonFunc = new CommonFuncionalities();

    @Dado("que acesso o site {string}")
    public void queAcessoOSite(String link) {
        Method method = stepExecutor.findMethod(commonFunc, "acessarSite", link);
        stepExecutor.execute(commonFunc, method, link);
    }

    @E("estou executando o teste")
    public void estouExecutandoOTeste(DataTable parametros) {
        parameters.estouExecutandoOTeste(parametros);
    }

    @E("insiro no campo {string} o valor {string}")
    public void insiroNoCampoOValor(String campo, String valor) {
        Method method = stepExecutor.findMethod(commonFunc, "inserirValorNoCampo", valor, campo);
        stepExecutor.execute(commonFunc, method, valor, campo);
    }

    @Quando("aperto a tecla {string}")
    public void apertoATecla(String tecla) {
        Method method = stepExecutor.findMethod(commonFunc, "apertarTecla", tecla);
        stepExecutor.execute(commonFunc, method, tecla);
    }

    @Então("visualizo o site {string} na lista de resultados")
    public void visualizoOSiteNaListaDeResultados(String site) {
        boolean r;
        Method method = stepExecutor.findMethod(commonFunc, "visualizarSite", site);
        r = (boolean) stepExecutor.execute(commonFunc, method, site);
        method = stepExecutor.findMethod(Assert.class, "assertTrue", boolean.class);
        stepExecutor.execute(null, method, r);
    }
}
