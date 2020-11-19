package br.com.projeto.web.steps;

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

}
