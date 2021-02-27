package br.com.projeto.evidence.utils;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import br.com.projeto.commons.Path;
import br.com.projeto.evidence.model.Evidence;
import br.com.projeto.evidence.model.ScreenCapture;

import static br.com.projeto.commons.Utils.DataUtils.*;

public class EvidenceUtils {

    private static final String path = Path.getEvidencePath();

    public static void main(String[] args) {
        generateEvidenceReport(new Evidence("Projeto de testes", "Teste01", "TST001", "Feliphe Jesus", "Sprint 1", "Falhou", null));
    }

    public static void generateEvidenceReport(final Evidence evidence) {
        Document document;
        String pdfFileName = path + evidence.getProjectName() + "_" + obterDataAtual(DATAHORA) + ".pdf";
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
        PdfPTable table1;
        PdfPCell screenCaptureCell = new PdfPCell(new Phrase("Descrição"));
        screenCaptureCell.setBackgroundColor(new BaseColor(210, 210, 210));
        screenCaptureCell.setVerticalAlignment(Element.ALIGN_TOP);
        screenCaptureCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        for (ScreenCapture sc : evidence.getScreenCaptureList()) {
            document.newPage();
            table1 = new PdfPTable(new float[] { 40f });
            table1.addCell(screenCaptureCell);
            table1.addCell(sc.getDescription() == null ? "Captura de tela." : sc.getDescription());
            image = Image.getInstance(sc.toByteArray());
            image.scaleToFit(500, 400);
            image.setAlignment(Image.ALIGN_CENTER);
            document.add(table1);
            document.add(new Paragraph(Chunk.NEWLINE));
            document.add(new Paragraph(Chunk.NEWLINE));
            document.add(image);
//            new Paragraph(sc.getDescription() == null ? "Screenshot:" : sc.getDescription())
        }
    }

    private static void createHeader(final Document document,  final Evidence evidence) throws DocumentException {
        PdfPTable table1 = new PdfPTable(new float[] { 30f });
        PdfPTable table2 = new PdfPTable(new float[] { 20f, 10f });
        PdfPTable table3 = new PdfPTable(new float[] { 10f, 10f, 10f });
        PdfPCell cellTestName = new PdfPCell(new Phrase("Caso de teste")),
                cellTestNumber = new PdfPCell(new Phrase("Número")),
                cellTesterName = new PdfPCell(new Phrase("Executor")),
                cellTestCycle = new PdfPCell(new Phrase("Ciclo")),
                cellTestStatus = new PdfPCell(new Phrase("Situação")),
                cellProjectName = new PdfPCell(new Phrase("Projeto"));
        cellProjectName.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTestName.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTestNumber.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTesterName.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTestCycle.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTestStatus.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellProjectName.setBackgroundColor(new BaseColor(210, 210, 210));
        cellTestName.setBackgroundColor(new BaseColor(210, 210, 210));
        cellTestNumber.setBackgroundColor(new BaseColor(210, 210, 210));
        cellTesterName.setBackgroundColor(new BaseColor(210, 210, 210));
        cellTestCycle.setBackgroundColor(new BaseColor(210, 210, 210));
        cellTestStatus.setBackgroundColor(new BaseColor(210, 210, 210));
        table1.addCell(cellProjectName);
        table2.addCell(cellTestName);
        table2.addCell(cellTestNumber);
        table3.addCell(cellTesterName);
        table3.addCell(cellTestCycle);
        table3.addCell(cellTestStatus);
        table1.addCell(new PdfPCell(new Phrase(evidence.getProjectName())));
        table2.addCell(new PdfPCell(new Phrase(evidence.getTestName())));
        table2.addCell(new PdfPCell(new Phrase(evidence.getTestNumber())));
        table3.addCell(new PdfPCell(new Phrase(evidence.getTesterName())));
        table3.addCell(new PdfPCell(new Phrase(evidence.getTestCycle())));
        table3.addCell(new PdfPCell(new Phrase(evidence.getTestStatus())));
        document.add(table1);
        document.add(table2);
        document.add(table3);
    }

    private static Document createDocument(final String pdfFileName) throws DocumentException, FileNotFoundException {
        Document doc = new Document(PageSize.A4, 10, 10, 10, 10);
        PdfWriter.getInstance(doc, new FileOutputStream(pdfFileName));
        return doc;
    }
}
