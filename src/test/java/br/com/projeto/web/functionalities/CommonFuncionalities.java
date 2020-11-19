package br.com.projeto.web.functionalities;

import org.openqa.selenium.Keys;
import br.com.projeto.commons.DSL;
import br.com.projeto.commons.BaseTest;
import br.com.projeto.web.pages.GooglePage;

public class CommonFuncionalities extends BaseTest {

    private DSL dsl = new DSL(webDriver);
    private GooglePage googlePage = new GooglePage(webDriver);

    public void acessarSite(String link) {
        webDriver.get(link);
        capturarTela("Acesso o site " + link + ":");
    }

    public void inserirValorNoCampo(String campo, String valor) {
        switch (campo) {
            case "Barra de Pesquisa":
                dsl.escrever(googlePage.getSearchBar(), valor);
                break;
            default:
                throw new IllegalArgumentException("O campo especificado não existe.");
        }
        capturarTela("Escrevo no campo " + campo + " o valor " + valor + ":");
    }

    public void apertarTecla(String tecla) {
        switch (tecla) {
            case "Enter":
                dsl.escrever(Keys.ENTER);
                break;
            default:
                throw new IllegalArgumentException("A tecla especificada não existe.");
        }
        capturarTela("Aperto a tecla " + tecla + ":");
    }

}
