package br.com.projeto.web.functionalities;

import br.com.projeto.commons.DSL;
import br.com.projeto.commons.BaseTest;
import org.openqa.selenium.By;

public class CommonFuncionalities extends BaseTest {

    private DSL dsl = new DSL(webDriver);

    public void acessarSite(String link) {
        webDriver.get(link);
        capturarTela("Acesso o site " + link + ":");
    }

    public void clicarNoBotao(String botao) {
        switch (botao) {
            case "teste":
                dsl.clicar(webDriver.findElement(By.xpath("teste")));
                break;
        }
        capturarTela("Clico no botão " + botao + ":");
    }
}
