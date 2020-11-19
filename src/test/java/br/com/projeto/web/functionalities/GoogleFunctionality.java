package br.com.projeto.web.functionalities;

import static org.junit.Assert.*;
import br.com.projeto.commons.DSL;
import br.com.projeto.commons.BaseTest;
import br.com.projeto.web.pages.GooglePage;

public class GoogleFunctionality extends BaseTest {

    private DSL dsl = new DSL(webDriver);
    private GooglePage googlePage = new GooglePage(webDriver);

    public void visualizarResultados() {
        capturarTela("Visualizo os resultados:");
        assertTrue(googlePage.getResultList().size() > 0);
    }

    public void naoVisualizarResultados() {
        capturarTela("Não visualizo os resultados:");
        assertEquals(0, googlePage.getResultList().size());
    }
}
