package br.com.projeto.configuration;

import java.io.IOException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import br.com.projeto.commons.BaseTest;
import br.com.projeto.evidence.model.EvidenceReport;
import br.com.projeto.evidence.model.enums.EvidenceType;
import br.com.projeto.exceptions.NoSuchAnnotationException;
import br.com.projeto.evidence.report.GenerateEvidenceReport;

public class Hooks extends BaseTest {

    @Before(value = "@evidence", order = 1)
    public void beforeEvidence() {
        initializeEvidence();
    }

    @Before(value = "@web", order = 2)
    public void beforeScenarioWeb(Scenario scenario) {
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

    @After(value = "@evidence", order = 1)
    public void afterEvidence(Scenario scenario) {
        report = new EvidenceReport(evidences, evidence.getEvidenceName(), evidence.getNomeExecutor(), evidence.getNomeProjeto(), errors);
        try {
            GenerateEvidenceReport.generareEvidenceReport(report, scenario, EvidenceType.PDF);
            System.out.println("Evidence generated successfully.");
        } catch (IOException ioException) {
            System.err.println("An error occurred while generating the evidence.");
            ioException.printStackTrace(System.err);
        }
    }

    @After(value = "@web", order = 2)
    public void afterScenarioWeb() {
        closeWeb();
    }

}
