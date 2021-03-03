package br.info.felseje.configuration.listeners;

import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.TestCase;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestCaseFinished;


public class TestCaseListener implements EventListener {

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
        Result result = event.getResult();
        TestCase testCase = event.getTestCase();
        System.out.println("Finished " + testCase.getName());
        if (result.getStatus() == Status.FAILED) {
            System.err.println("Failed " + testCase.getName());
            result.getError().printStackTrace(System.err);
        }
    }
}
