package br.info.felseje.evidence.model;

import java.util.List;
import java.util.ArrayList;
import io.cucumber.java.Status;

/**
 * Class that contains the evidence data.
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class Evidence {

    public Evidence() {
    }

    public Evidence(String testName, String testIdNumber, String testCycle, String testerName, String testRuntime, String testDate, Status testStatus, Throwable testError, List<ScreenCapture> screenCaptureList) {
        this.testName = testName;
        this.testIdNumber = testIdNumber;
        this.testCycle = testCycle;
        this.testerName = testerName;
        this.testRuntime = testRuntime;
        this.testDate = testDate;
        this.testStatus = testStatus;
        this.testError = testError;
        this.screenCaptureList = screenCaptureList;
    }

    private String testName;
    private String testIdNumber;
    private String testCycle;
    private String testerName;
    private String testRuntime;
    private String testDate;
    private Status testStatus;
    private Throwable testError;
    private List<ScreenCapture> screenCaptureList = new ArrayList<>();

    public String getTestName() {
        return testName;
    }

    public Evidence setTestName(String testName) {
        this.testName = testName;
        return this;
    }

    public String getTestIdNumber() {
        return testIdNumber;
    }

    public Evidence setTestIdNumber(String testIdNumber) {
        this.testIdNumber = testIdNumber;
        return this;
    }

    public String getTestCycle() {
        return testCycle;
    }

    public Evidence setTestCycle(String testCycle) {
        this.testCycle = testCycle;
        return this;
    }

    public String getTesterName() {
        return testerName;
    }

    public Evidence setTesterName(String testerName) {
        this.testerName = testerName;
        return this;
    }

    public String getTestRuntime() {
        return testRuntime;
    }

    public Evidence setTestRuntime(String testRuntime) {
        this.testRuntime = testRuntime;
        return this;
    }

    public String getTestDate() {
        return testDate;
    }

    public Evidence setTestDate(String testDate) {
        this.testDate = testDate;
        return this;
    }

    public Status getTestStatus() {
        return testStatus;
    }

    public Evidence setTestStatus(Status testStatus) {
        this.testStatus = testStatus;
        return this;
    }

    public Throwable getTestError() {
        return testError;
    }

    public Evidence setTestError(Throwable testError) {
        this.testError = testError;
        return this;
    }

    public List<ScreenCapture> getScreenCaptureList() {
        return screenCaptureList;
    }

    public Evidence setScreenCaptureList(List<ScreenCapture> screenCaptureList) {
        this.screenCaptureList = screenCaptureList;
        return this;
    }
}
