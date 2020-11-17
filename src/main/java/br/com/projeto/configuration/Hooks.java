package br.com.projeto.configuration;

import java.io.IOException;
import java.util.ArrayList;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import br.com.projeto.commons.Utils;
import br.com.projeto.bean.enums.Web;
import br.com.projeto.commons.BaseTest;
import br.com.projeto.evidence.model.EvidenceReport;
import br.com.projeto.evidence.model.enums.EvidenceType;
import br.com.projeto.evidence.report.GenerateEvidenceReport;
import io.cucumber.java.Scenario;

public class Hooks extends BaseTest {

    @Before(value = "@web")
    public void beforeScenarioWeb() {
        evidences = new ArrayList<>();
        initializeWebApplication(Web.CHROME);
    }

    @After(value = "@web")
    public void afterScenarioWeb(Scenario scenario) {
        report = new EvidenceReport(evidences, evidence.getNumeroCT().concat("_".concat(Utils.obterDataAtual(Utils.DATAHORA))), evidence.getNomeExecutor(), evidence.getNomeProjeto(), errors);
        try {
            GenerateEvidenceReport.generareEvidenceReport(report, scenario, EvidenceType.PDF);
        } catch (IOException ioException) {
            System.err.println("Um erro ocorreu ao gerar as evidencias.");
            ioException.printStackTrace(System.err);
        } finally {
            closeWeb();
        }
    }

}
