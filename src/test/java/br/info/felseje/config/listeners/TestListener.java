package br.info.felseje.config.listeners;

import com.itextpdf.text.DocumentException;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestCaseFinished;
import br.info.felseje.commons.BaseTest;
import br.info.felseje.evidence.EvidenceFactory;

import java.io.IOException;

/**
 * Captures initialization and completion events of the tests performed.
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class TestListener extends BaseTest implements EventListener {

    @Override
    public void setEventPublisher(final EventPublisher evtPublisher) {
        evtPublisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
        evtPublisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
    }

    /**
     *
     * @param event the event.
     */
    public void onTestCaseStarted(TestCaseStarted event) {
        io.cucumber.plugin.event.TestCase testCase = event.getTestCase();
        System.out.println("Starting " + testCase.getName());
    }

    /**
     *
     * @param event the event.
     */
    private void onTestCaseFinished(final TestCaseFinished event) {
        io.cucumber.plugin.event.TestCase testCase = event.getTestCase();
        Status status = event.getResult().getStatus();
        Throwable error = event.getResult().getError();
        System.out.println("Generating evidence file for scenario: " + testCase.getName());
        if (status == Status.FAILED) {
            evidence.setTestError(error);
        }
        try {
            EvidenceFactory.build(evidence);
        } catch (DocumentException | IOException e) {
            System.err.println("Error while generating evidence file for scenario: " + testCase.getName());
            e.printStackTrace(System.err);
        }
    }
}
