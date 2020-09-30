package br.com.projeto.configuration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import br.com.projeto.bean.enums.Web;
import java.net.MalformedURLException;
import br.com.projeto.commons.BaseTest;
import br.com.projeto.bean.enums.Desktop;

public class Hooks extends BaseTest {

    @Before(value = "@web")
    public void beforeScenarioWeb() {
        initializeWebApplication(Web.CHROME);
    }

    @Before(value = "@desktop")
    public void beforeScenarioDesktop() throws MalformedURLException {
        initializeDesktopApplication(Desktop.WINIUM);
    }

    @After(value = "@web")
    public void afterScenarioWeb() {
        closeWeb();
    }

    @After(value = "@desktop")
    public void afterScenarioDesktop() {
        closeDesktop();
    }
}
