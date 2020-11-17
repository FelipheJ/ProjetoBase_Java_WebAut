package br.com.projeto.evidence.report;

import java.io.File;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Objects;
import java.io.IOException;
import java.util.Properties;
import javax.imageio.ImageIO;
import java.io.FileOutputStream;
import java.awt.image.BufferedImage;

import io.cucumber.java.Scenario;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRException;
import br.com.projeto.evidence.utils.EvidenceUtils;
import br.com.projeto.evidence.model.EvidenceReport;
import net.sf.jasperreports.engine.JasperFillManager;
import br.com.projeto.evidence.model.SeleniumEvidence;
import net.sf.jasperreports.engine.JasperExportManager;
import br.com.projeto.evidence.model.enums.EvidenceType;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;

public class GenerateEvidenceReport {

    public static void generareEvidenceReport(EvidenceReport evidenceReport, Scenario scenario, EvidenceType reportType) throws IOException {

        Properties properties;
        JasperPrint jasperPrint;
        Map<String, Object> parameters;
        JRBeanCollectionDataSource datasource;
        BufferedImage imageCompany, imageClient;
        String evidenceDir, companyImage, customerImage, reportName, tester, project, exception;
        List<SeleniumEvidence> data = evidenceReport.getEvidenceList();

        properties = EvidenceUtils.loadProperties("init.properties");
        evidenceDir = System.getProperty("user.dir")
                + System.getProperty("file.separator")
                + properties.getProperty("evidence.dir")
                + System.getProperty("file.separator");

        EvidenceUtils.createEvidenceDirectory(evidenceDir);

        if (scenario.isFailed()) {
            evidenceDir += System.getProperty("file.separator") + "Failed";
        } else {
            evidenceDir += System.getProperty("file.separator") + "Passed";
        }

        try {

            companyImage = properties.getProperty("image.company.path");
            customerImage = properties.getProperty("image.customer.path");

            if (Objects.nonNull(companyImage)) {
                imageCompany = ImageIO.read(new File(companyImage));
            } else {
                imageCompany = null;
            }

            if (Objects.nonNull(customerImage)) {
                imageClient = ImageIO.read(new File(customerImage));
            } else {
                imageClient = null;
            }

            reportName = evidenceReport.getReportName();
            if (Objects.isNull(reportName)) {
                reportName = "";
            }

            tester = evidenceReport.getTester();
            if (Objects.isNull(tester)) {
                tester = "";
            }

            project = evidenceReport.getProject();
            if (Objects.isNull(project)) {
                project = "";
            }

            exception = evidenceReport.getExceptionString();
            if (Objects.isNull(exception)) {
                exception = "";
            }

            parameters = new HashMap<>();
            if (Objects.nonNull(exception)) {
                parameters.put("SEL_EXCEPTION", exception);
            }

            parameters.put("SEL_COMPANY_LOGO", imageCompany);
            parameters.put("SEL_CUSTOMER_LOGO", imageClient);
            parameters.put("SEL_PROJECT", project);
            parameters.put("SEL_TESTER", tester);
            parameters.put("SEL_LABEL_EVINDENCE_TITLE", properties.getProperty("label.evidenceReport"));
            parameters.put("SEL_LABEL_PROJECT", properties.getProperty("label.projetc"));
            parameters.put("SEL_LABEL_TESTER", properties.getProperty("label.tester"));
            parameters.put("SEL_LABEL_STATUS", properties.getProperty("label.status"));
            parameters.put("SEL_LABEL_PASS", properties.getProperty("label.status.pass"));
            parameters.put("SEL_LABEL_FAILED", properties.getProperty("label.status.failed"));
            parameters.put("SEL_LABEL_EVIDENCE_REPORT", properties.getProperty("label.evidenceReport"));
            parameters.put("SEL_LABEL_DATE", properties.getProperty("label.date"));
            parameters.put("SEL_LABEL_FOOTER", properties.getProperty("label.footer"));
            parameters.put("SEL_LABEL_ERROR_DETAIL", properties.getProperty("label.errorDetail"));
            parameters.put("SEL_LABEL_PAGE", properties.getProperty("label.page"));
            parameters.put("SEL_LABEL_COMPANY_NAME", properties.getProperty("label.company.name"));

            datasource = new JRBeanCollectionDataSource(data);
            jasperPrint = JasperFillManager.fillReport(properties.getProperty("evidence.file"), parameters, datasource);

            switch (reportType) {
                case PDF:
                    JasperExportManager.exportReportToPdfFile(jasperPrint, evidenceDir + reportName + ".pdf");
                    break;
                case DOC:
                    JRDocxExporter exporter = new JRDocxExporter();
                    File file = new File(evidenceDir + reportName + ".doc");
                    FileOutputStream os = new FileOutputStream(file);
                    exporter.setParameter(JRDocxExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRDocxExporterParameter.CHARACTER_ENCODING, "UTF-8");
                    exporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, os);
                    exporter.exportReport();
                    os.close();
                    break;
                case HTML:
                    JasperExportManager.exportReportToHtmlFile(jasperPrint, evidenceDir + reportName + ".html");
                    break;
                default:
                    throw new IllegalArgumentException("The specified file extension is not recognized.");
            }

        } catch (SecurityException securityException) {
            securityException.printStackTrace();
        } catch (JRException jrException) {
            jrException.printStackTrace();
        }
    }

}
