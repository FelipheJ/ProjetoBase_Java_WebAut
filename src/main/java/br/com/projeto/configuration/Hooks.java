package br.com.projeto.configuration;

import java.io.IOException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.AfterStep;
import br.com.projeto.commons.BaseTest;
import br.com.projeto.exceptions.NoSuchAnnotationException;

public class Hooks extends BaseTest {

    @Before(value = "@evidence", order = 1)
    public void beforeEvidenceAnnotation() {
        initializeEvidence();
    }

    @Before(value = "@web", order = 2)
    public void beforeWebAnnotation(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@chrome")) {
            initializeWebApplication("CHROME");
        } else if (scenario.getSourceTagNames().contains("@firefox")) {
            initializeWebApplication("FIREFOX");
        } else if (scenario.getSourceTagNames().contains("@opera")) {
            initializeWebApplication("OPERA");
        } else if (scenario.getSourceTagNames().contains("@safari")) {
            initializeWebApplication("SAFARI");
        } else if (scenario.getSourceTagNames().contains("@edge")) {
            initializeWebApplication("EDGE");
        } else if (scenario.getSourceTagNames().contains("@ie")) {
            initializeWebApplication("IE");
        } else {
            throw new NoSuchAnnotationException("Browser annotation is not specified.");
        }
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        screenshot(scenario);
    }

    @After(value = "@evidence", order = 1)
    public void afterEvidenceAnnotation(Scenario scenario) {
        System.out.println("Neste step, a evidência do cenário " + scenario.getName() + " devem ser geradas.");
    }

    @After(value = "@web", order = 2)
    public void afterWebAnnotation() {
        closeWeb();
    }
}
