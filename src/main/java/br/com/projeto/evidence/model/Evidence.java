package br.com.projeto.evidence.model;

import java.util.List;
import java.util.ArrayList;

public class Evidence {

    public Evidence() {
    }

    public Evidence(String projectName, String testName, String testNumber, String testerName, String testCycle, String testStatus, List<ScreenCapture> screenCaptureList) {
        this.projectName = projectName;
        this.testName = testName;
        this.testNumber = testNumber;
        this.testerName = testerName;
        this.testCycle = testCycle;
        this.testStatus = testStatus;
        this.screenCaptureList = screenCaptureList;
    }

    private String projectName;     /* ProjectName     */
    private String testName;        /* TestName     */
    private String testNumber;      /* TestNumber   */
    private String testerName;      /* ExecutorName */
    private String testCycle;       /* CycleNumber  */
    private String testStatus;      /* TestStatus   */
    private List<ScreenCapture> screenCaptureList = new ArrayList<>();

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(String testNumber) {
        this.testNumber = testNumber;
    }

    public String getTesterName() {
        return testerName;
    }

    public void setTesterName(String testerName) {
        this.testerName = testerName;
    }

    public String getTestCycle() {
        return testCycle;
    }

    public void setTestCycle(String testCycle) {
        this.testCycle = testCycle;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public List<ScreenCapture> getScreenCaptureList() {
        return screenCaptureList;
    }

    public void setScreenCaptureList(List<ScreenCapture> screenCaptureList) {
        this.screenCaptureList = screenCaptureList;
    }
}
