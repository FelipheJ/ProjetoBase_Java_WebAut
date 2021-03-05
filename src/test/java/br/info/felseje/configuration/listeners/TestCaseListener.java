package br.info.felseje.configuration.listeners;

import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.TestCase;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestCaseFinished;
import br.info.felseje.commons.BaseTest;
import br.info.felseje.evidence.utils.EvidenceUtils;

public class TestCaseListener extends BaseTest implements EventListener {

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
        TestCase testCase = event.getTestCase();
        System.out.println("Starting " + testCase.getName());
    }

    /**
     *
     * @param event the event.
     */
    private void onTestCaseFinished(final TestCaseFinished event) {
        TestCase testCase = event.getTestCase();
        Status status = event.getResult().getStatus();
        Throwable error = event.getResult().getError();
        System.out.println("Generating evidence file for scenario: " + testCase.getName());
        if (status == Status.FAILED) {
            evidence.setTestError(error.toString());
        }
        if (status == Status.FAILED || status == Status.PASSED) {
            EvidenceUtils.generateEvidenceReport(evidence);
        } else {
            System.err.println("Evidence files has not been generated to status " + status);
        }
    }
}
