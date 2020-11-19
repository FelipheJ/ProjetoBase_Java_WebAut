package br.com.projeto.web.steps;

import io.cucumber.java.pt.*;
import br.com.projeto.commons.StepExecutor;
import br.com.projeto.web.functionalities.GoogleFunctionality;

import java.lang.reflect.Method;

public class GoogleSteps {

    private StepExecutor stepExecutor = new StepExecutor();
    private GoogleFunctionality googleFunc = new GoogleFunctionality();

    @Então("visualizo os resultados da pesquisa")
    public void visualizoOsResultadosDaPesquisa() {
        Method method = stepExecutor.findMethod(googleFunc, "visualizarResultados");
        stepExecutor.execute(googleFunc, method);
    }

    @Então("não visualizo os resultados da pesquisa")
    public void nãoVisualizoOsResultadosDaPesquisa() {
        Method method = stepExecutor.findMethod(googleFunc, "naoVisualizarResultados");
        stepExecutor.execute(googleFunc, method);
    }
}
