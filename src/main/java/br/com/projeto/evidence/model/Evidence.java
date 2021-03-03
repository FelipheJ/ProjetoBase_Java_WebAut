package br.com.projeto.evidence.model;

import java.util.List;
import java.util.ArrayList;

public class Evidence {

    public Evidence() {
    }

    public Evidence(Evidence e) {
        this.systemName = e.systemName;
        this.systemVersion = e.systemVersion;
        this.testId = e.testId;
        this.testName = e.testName;
        this.testerName = e.testerName;
        this.testDate = e.testDate;
        this.testCycle = e.testCycle;
        this.testStatus = e.testStatus;
        this.screenCaptureList = e.screenCaptureList;
    }

    public Evidence(String systemName, String systemVersion, String testId, String testName, String testerName, String testDate, String testCycle, String testStatus, List<ScreenCapture> screenCaptureList) {
        this.systemName = systemName;
        this.systemVersion = systemVersion;
        this.testId = testId;
        this.testName = testName;
        this.testerName = testerName;
        this.testDate = testDate;
        this.testCycle = testCycle;
        this.testStatus = testStatus;
        this.screenCaptureList = screenCaptureList;
    }

    private String systemName;      /* ProjectName          */
    private String systemVersion;   /* ProjectVersion       */
    private String testId;          /* TestIDNumber         */
    private String testName;        /* TestName             */
    private String testerName;      /* ExecutorName         */
    private String testDate;        /* TestExecutionDate    */
    private String testCycle;       /* CycleNumber          */
    private String testStatus;      /* TestStatus           */
    private List<ScreenCapture> screenCaptureList = new ArrayList<>();

    public String getSystemName() {
        return systemName;
    }

    public Evidence setSystemName(String systemName) {
        this.systemName = systemName;
        return this;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public Evidence setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
        return this;
    }

    public String getTestId() {
        return testId;
    }

    public Evidence setTestId(String testId) {
        this.testId = testId;
        return this;
    }

    public String getTestName() {
        return testName;
    }

    public Evidence setTestName(String testName) {
        this.testName = testName;
        return this;
    }

    public String getTesterName() {
        return testerName;
    }

    public Evidence setTesterName(String testerName) {
        this.testerName = testerName;
        return this;
    }

    public String getTestDate() {
        return testDate;
    }

    public Evidence setTestDate(String testDate) {
        this.testDate = testDate;
        return this;
    }

    public String getTestCycle() {
        return testCycle;
    }

    public Evidence setTestCycle(String testCycle) {
        this.testCycle = testCycle;
        return this;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public Evidence setTestStatus(String testStatus) {
        this.testStatus = testStatus;
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
