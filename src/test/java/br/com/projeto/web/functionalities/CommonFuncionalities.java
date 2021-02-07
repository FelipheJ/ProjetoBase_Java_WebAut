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
    }

    public void inserirValorNoCampo(String valor, String campo) {
        switch (campo) {
            case "Barra de busca":
                dsl.escrever(googlePage.getBarraDeBusca(), valor);
                break;
            default:
                throw new IllegalArgumentException("O campo especificado não foi implementado.");
        }
    }

    public boolean visualizarSite(String site) {
        boolean retorno;
        switch(site) {
            case "GitHub":
                retorno = dsl.estaVisivel(googlePage.getGitHubLink());
                break;
            default:
                throw new IllegalArgumentException("O site especificado não foi implementado.");
        }
        return retorno;
    }

    public void apertarTecla(String tecla) {
        switch (tecla) {
            case "Enter":
                dsl.escrever(Keys.ENTER);
                break;
            default:
                throw new IllegalArgumentException("A tecla especificada não foi implementada.");
        }
    }
}
