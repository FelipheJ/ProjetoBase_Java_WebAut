package br.com.projeto.evidence.report;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import com.rajatthareja.reportbuilder.ReportBuilder;

public class generateHTMLReport {

	public static void main(String... args) throws IOException {

		LocalDateTime atual = LocalDateTime.now();
		String path = System.getProperty("user.dir");
		ReportBuilder reportBuilder = new ReportBuilder();
		List<Object> cucumberJsonReports = new ArrayList<>();
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		Properties properties = loadProperties(path + "\\report.properties");

		reportBuilder.setReportDirectory(path + "\\target\\");
		reportBuilder.setReportFileName((String) properties.get("filename"));
		reportBuilder.setReportTitle((String) properties.get("reporttitle"));
		reportBuilder.setReportColor((String) properties.get("reportcolor"));
		reportBuilder.setAdditionalInfo("Ambiente", (String) properties.get("enviroment"));
		reportBuilder.setAdditionalInfo("Data e hora", formatoDataHora.format(atual));
		if (((String) properties.get("voicecontrol")).toLowerCase().equals("true")) {
			reportBuilder.enableVoiceControl();
		}
		cucumberJsonReports.add(new File(path + "\\target\\cucumber.json"));
		cucumberJsonReports.add(new File(path + "\\target\\"));

		reportBuilder.build(cucumberJsonReports);
	}

	private static Properties loadProperties(String path) throws IOException {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(path));
		} catch(IOException ex) {
			ex.printStackTrace(System.err);
			throw new IOException("Erro ao carregar o arquivo 'report.properties'.");
		}
		return p;
	}
}

