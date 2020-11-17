package br.com.projeto.web.functionalities;

import br.com.projeto.commons.DSL;
import br.com.projeto.commons.BaseTest;

public class CommonFuncionalities extends BaseTest {

    private DSL dsl = new DSL(webDriver);

    public void acessarSite(String link) {
        webDriver.get(link);
        capturarTela("Acesso o site " + link + ":");
    }

}
