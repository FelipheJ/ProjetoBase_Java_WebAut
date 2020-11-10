package br.com.projeto.web.steps;

import io.cucumber.java.pt.Dado;
import java.lang.reflect.Method;
import br.com.projeto.commons.StepExecutor;
import br.com.projeto.web.functionalities.CommonFuncionalities;

public class CommonSteps {

    private StepExecutor stepExecutor = new StepExecutor();
    private CommonFuncionalities commonFunc = new CommonFuncionalities();

    @Dado("que acesso o site {string}")
    public void queAcessoOSite(String link) {
        Method method = stepExecutor.findMethod(commonFunc, "acessarSite", link);
        stepExecutor.execute(commonFunc, method, link);
    }
}
