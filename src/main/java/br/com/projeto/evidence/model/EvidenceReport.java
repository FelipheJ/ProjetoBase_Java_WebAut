package br.com.projeto.evidence.model;

import java.util.List;

public class EvidenceReport {

	private String tester = null;
	private String project = null;
	private String reportName = null;
	private String exceptionString = null;
	private List<SeleniumEvidence> evidenceList = null;

	public EvidenceReport(List<SeleniumEvidence> evidenceList, String reportName, String tester,
			String project, String exceptionString) {
		this.evidenceList = evidenceList;
		this.reportName = reportName;
		this.tester = tester;
		this.project = project;
		this.exceptionString = exceptionString;
	}

	public List<SeleniumEvidence> getEvidenceList() {
		return evidenceList;
	}

	public void setEvidenceList(List<SeleniumEvidence> evidenceList) {
		this.evidenceList = evidenceList;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getTester() {
		return tester;
	}
	
	public void setTester(String tester) {
		this.tester = tester;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getExceptionString() {
		return exceptionString;
	}

	public void setExceptionString(String exceptionString) {
		this.exceptionString = exceptionString;
	}
	
}
