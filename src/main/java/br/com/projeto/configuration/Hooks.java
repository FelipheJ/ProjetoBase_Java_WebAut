package br.com.projeto.configuration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import br.com.projeto.bean.enums.Web;
import br.com.projeto.commons.BaseTest;

public class Hooks extends BaseTest {

    @Before(value = "@web")
    public void beforeScenarioWeb() {
        initializeWebApplication(Web.CHROME);
    }

    @After(value = "@web")
    public void afterScenarioWeb() {
        closeWeb();
    }

}
