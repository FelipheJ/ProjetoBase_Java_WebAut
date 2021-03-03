package br.info.felseje.evidence.utils;

import java.util.Arrays;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import br.info.felseje.commons.Path;
import br.info.felseje.commons.Utils.DataUtils;
import br.info.felseje.evidence.model.Evidence;
import br.info.felseje.evidence.model.ScreenCapture;

public class EvidenceUtils {

    private static final String path = Path.getEvidencePath();

    public static void main(String[] args) {
        generateEvidenceReport(new Evidence()
                .setSystemName("SysTech")
                .setSystemVersion("1.0.0")
                .setTestName("Validar que consigo gerar pdf")
                .setTestId("Examp01a")
                .setTestCycle("Sprint 1")
                .setTesterName("Feliphe Jesus")
                .setTestStatus("Passed")
        );
    }

    public static void generateEvidenceReport(final Evidence evidence) {
        Document document;
        String pdfFileName = path + evidence.getSystemName() + "_" + DataUtils.obterDataAtual(DataUtils.DATAHORA) + ".pdf";
        try {
            document = createDocument(pdfFileName);
            document.open();
            createHeader(document, evidence);
            fillDocument(document, evidence);
            document.close();
        } catch(IOException ioEx) {
            ioEx.printStackTrace();
        } catch (DocumentException docEx) {
            docEx.printStackTrace(System.err);
        }
    }

    private static void fillDocument( Document document,  final Evidence evidence) throws DocumentException, IOException {
        Image image;
        Paragraph paragraph;
        for (ScreenCapture sc : evidence.getScreenCaptureList()) {
            document.newPage();
            image = Image.getInstance(sc.toByteArray());
            image.scaleToFit(500, 400);
            image.setAlignment(Image.ALIGN_CENTER);
            paragraph = new Paragraph(Chunk.NEWLINE);
            document.add(paragraph);
            document.add(image);
            paragraph = new Paragraph(new Phrase("Captura de tela, " + DataUtils.formatarData(sc.getTimestamp(), "dd/MM/yyyy, hh:mm:ss"), new Font(Font.FontFamily.UNDEFINED, 10)));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
        }
    }

    private static void createHeader(final Document document,  final Evidence evidence) throws DocumentException {
        PdfPTable table1 = new PdfPTable(new float[] { 70f }),
                table2 = new PdfPTable(new float[] { 10f, 25f, 10f, 25f }),
                table3 = new PdfPTable(new float[] { 10f, 60f }),
                table4 = new PdfPTable(new float[] { 10f, 25f, 10f, 25f }),
                table5 = new PdfPTable(new float[] { 10f, 25f, 10f, 25f });
        PdfPCell cellTitle = new PdfPCell(new Phrase("Evidências de teste")),
                cellSystemName = new PdfPCell(new Phrase("Sistema")),
                cellSystemVersion = new PdfPCell(new Phrase("Versão")),
                cellTestName = new PdfPCell(new Phrase("Teste")),
                cellTestNumber = new PdfPCell(new Phrase("ID")),
                cellTestCycle = new PdfPCell(new Phrase("Ciclo")),
                cellTesterName = new PdfPCell(new Phrase("Executor")),
                cellTestStatus = new PdfPCell(new Phrase("Situação"));
        Font failed = new Font(Font.FontFamily.UNDEFINED),
                passed = new Font(Font.FontFamily.UNDEFINED);

        failed.setColor(BaseColor.RED);
        passed.setColor(BaseColor.GREEN);
        cellTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellSystemName.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellSystemVersion.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTestName.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTestNumber.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTestCycle.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTesterName.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTestStatus.setHorizontalAlignment(Element.ALIGN_CENTER);

        Arrays.asList(cellTitle, cellSystemName, cellSystemVersion, cellTestName, cellTestNumber, cellTestCycle, cellTesterName, cellTestStatus)
                .forEach(c -> c.setBackgroundColor(new BaseColor(210, 210, 210)));

        PdfPCell cell1 = new PdfPCell(new Phrase(evidence.getSystemName())),
                cell2 = new PdfPCell(new Phrase(evidence.getSystemVersion())),
                cell3 = new PdfPCell(new Phrase(evidence.getTestName())),
                cell4 = new PdfPCell(new Phrase(evidence.getTestId())),
                cell5 = new PdfPCell(new Phrase(evidence.getTestCycle())),
                cell6 = new PdfPCell(new Phrase(evidence.getTesterName())),
                cell7;

        if (evidence.getTestStatus().toLowerCase().equals("passed")) {
            cell7 = new PdfPCell(new Phrase(evidence.getTestStatus(), passed));
        } else {
            cell7 = new PdfPCell(new Phrase(evidence.getTestStatus(), failed));
        }

        Arrays.asList(cell1, cell2, cell3, cell4, cell5, cell6, cell7).forEach(c -> c.setHorizontalAlignment(Element.ALIGN_CENTER));
        table1.addCell(cellTitle);
        table2.addCell(cellSystemName);
        table2.addCell(cell1);
        table2.addCell(cellSystemVersion);
        table2.addCell(cell2);
        table3.addCell(cellTestName);
        table3.addCell(cell3);
        table4.addCell(cellTestNumber);
        table4.addCell(cell4);
        table4.addCell(cellTestCycle);
        table4.addCell(cell5);
        table5.addCell(cellTesterName);
        table5.addCell(cell6);
        table5.addCell(cellTestStatus);
        table5.addCell(cell7);

        for (PdfPTable table : Arrays.asList(table1, table2, table3, table4, table5)) {
            document.add(table);
        }
    }

    private static Document createDocument(final String pdfFileName) throws DocumentException, FileNotFoundException {
        Document doc = new Document(PageSize.A4, 10, 10, 10, 10);
        PdfWriter.getInstance(doc, new FileOutputStream(pdfFileName));
        return doc;
    }
}
