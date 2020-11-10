package br.com.projeto.web.functionalities;

import br.com.projeto.commons.BaseTest;

public class CommonFuncionalities extends BaseTest {

    public void acessarSite(String link) {
        webDriver.get(link);
        capturarTela("Acesso o site " + link + ":");
    }
}
