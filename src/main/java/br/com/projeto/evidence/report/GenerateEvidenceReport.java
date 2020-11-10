package br.com.projeto.evidence.report;

import java.io.File;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.io.IOException;
import java.util.Properties;
import javax.imageio.ImageIO;
import java.io.FileOutputStream;
import java.awt.image.BufferedImage;
import br.com.projeto.evidence.model.enums.EvidenceType;
import br.com.projeto.evidence.model.EvidenceReport;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRException;
import br.com.projeto.evidence.model.SeleniumEvidence;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperExportManager;
import br.com.projeto.evidence.utils.SeleniumEvidenceUtils;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;

public class GenerateEvidenceReport {

    public static void generareEvidenceReport(EvidenceReport evidenceReport, EvidenceType reportType) throws IOException {
    	List<SeleniumEvidence> data = evidenceReport.getEvidenceList();
        
        Properties properties = SeleniumEvidenceUtils.loadProperties();
        String evidenceDir = System.getProperty("user.dir") + System.getProperty("file.separator") + 
        		properties.getProperty("evidence.dir") + System.getProperty("file.separator");
        
        createEvidenceDir(evidenceDir);

        try {

            String companyImage = properties.getProperty("image.company.path");
            String customerImage = properties.getProperty("image.customer.path");

            BufferedImage imageCompany;
            BufferedImage imageClient;

            if (companyImage == null || companyImage.equals("null")) {
                imageCompany = null;
            } else {
                imageCompany = ImageIO.read(new File(companyImage));
            }

            if (customerImage == null || customerImage.equals("null")) {
                imageClient = null;
            } else {
                imageClient = ImageIO.read(new File(customerImage));
            }

            String reportName = evidenceReport.getReportName();
            if (reportName == null) {
                reportName = "";
            }

            String tester = evidenceReport.getTester();
            if (tester == null) {
                tester = "";
            }

            String project = evidenceReport.getProject();
            if (project == null) {
                project = "";
            }

            String exception = evidenceReport.getExceptionString();
            if (exception == null) {
                exception = "";
            }

            Map<String, Object> parameters = new HashMap<String, Object>();
            if (exception != null) {
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

            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(data);
            JasperPrint jasperPrint = JasperFillManager.fillReport(properties.getProperty("evidence.file"), parameters, datasource);
            
            switch (reportType) {
			case PDF:
				JasperExportManager.exportReportToPdfFile(jasperPrint, evidenceDir + reportName + ".pdf");
				break;
			case DOC:
	            JRDocxExporter exporter = new JRDocxExporter();
	            File archivo = new File(evidenceDir + reportName + ".doc");
	            FileOutputStream os = new FileOutputStream(archivo);
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
				break;
			}
            
        } catch (SecurityException ex) {
            ex.printStackTrace();
        } catch (JRException jre) {
            jre.printStackTrace();
        }
    }

    private static boolean createEvidenceDir(String directory) {
        boolean dirExists = false;
        
        try {
            File dir = new File(directory);
            boolean exists = dir.exists();

            if (!exists) {
                dir.mkdir();
                dirExists = false;
            } else {
                dirExists = true;
            }
        } catch (SecurityException se) {
            se.printStackTrace();
        }
        return dirExists;
    }
}
