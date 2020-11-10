package br.com.projeto.commons;

import java.util.ArrayList;
import junit.framework.TestCase;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import br.com.projeto.evidence.model.EvidenceReport;
import br.com.projeto.bean.interfaces.WebApplication;
import br.com.projeto.evidence.model.SeleniumEvidence;

public class BaseTest {

    /* Atributos da evidência */
    protected static String errors = null;
    protected static EvidenceReport report = null;
    protected static ArrayList<SeleniumEvidence> evidences = null;

    /* Atributos do driver */
    protected static WebDriver webDriver;
    protected static final int MAX_TIME_WAIT = 20;
    protected static WebDriverWait webDriverWaiter;

    protected void initializeWebApplication(WebApplication webApplication) {
        webDriver = webApplication.getDriver();
        //webDriver.manage().window().fullscreen();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriverWaiter = new WebDriverWait(webDriver, MAX_TIME_WAIT);
    }

    protected static void closeWeb() {
        webDriver.quit();
    }

    protected String getScrrenshotAsBase64(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
    }

    /**
     * Captura uma foto da tela do browser.
     * @param descricao - mensagem descrevendo o contexto da imagem capturada.
     */
    protected void capturarTela(String descricao) {
        try {
            evidences.add(new SeleniumEvidence(descricao, getScrrenshotAsBase64(webDriver)));
        } catch(Exception exception) {
            System.err.println("Nao foi possivel capturar a tela.");
            exception.printStackTrace(System.err);
        }
    }

    protected void setError(Throwable t, WebDriver driver)  {
        try {
            evidences.add(new SeleniumEvidence(t.getLocalizedMessage(), getScrrenshotAsBase64(driver)));
            errors = t.toString();
            TestCase.fail(t.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
