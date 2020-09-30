package br.com.projeto.commons;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import br.com.projeto.bean.interfaces.WebApplication;

public class BaseTest {

    protected static WebDriver webDriver;
    protected static final int MAX_TIME_WAIT = 20;
    protected static WebDriverWait webDriverWaiter;

    protected void initializeWebApplication(WebApplication webApplication) {
        webDriver = webApplication.getDriver();
        webDriver.manage().window().fullscreen();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriverWaiter = new WebDriverWait(webDriver, MAX_TIME_WAIT);
    }

    protected static void closeWeb() {
        webDriver.quit();
    }
}
