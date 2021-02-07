package br.com.projeto.evidence.model;

import java.util.ArrayList;

public class Evidence {

    public Evidence() {
    }

    private String name;
    private String number;
    private String tester;
    private String cycle;
    private String status;
    private ArrayList<String> screenCaptureList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getScreenCaptureList() {
        return screenCaptureList;
    }

    public void setScreenCaptureList(ArrayList<String> screenCaptureList) {
        this.screenCaptureList = screenCaptureList;
    }
}
