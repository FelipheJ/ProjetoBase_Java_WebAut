package br.info.felseje.evidence.utils;

import java.util.Arrays;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import br.info.felseje.commons.Path;
import br.info.felseje.commons.Utils.DataUtils;
import br.info.felseje.evidence.model.Evidence;
import br.info.felseje.evidence.model.ScreenCapture;
import br.info.felseje.evidence.enums.TableWidthImpl;

import static br.info.felseje.evidence.utils.PDFCreator.*;

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

    private static void fillDocument(Document document,  final Evidence evidence) throws DocumentException, IOException {
        Paragraph paragraph;
        for (ScreenCapture sc : evidence.getScreenCaptureList()) {
            document.newPage();
            document.add(getNewLine());
            document.add(setImageAdjustment(Image.getInstance(sc.toByteArray())));
            paragraph = getParagraph("Captura de tela, " + DataUtils.formatarData(sc.getTimestamp(), "dd/MM/yyyy, hh:mm:ss"), getFont(10));
            setParagraphAlignment(paragraph, 1);
            document.add(paragraph);
        }
    }

    private static void createHeader(final Document document,  final Evidence evidence) throws DocumentException {
        Font failed = new Font(Font.FontFamily.UNDEFINED),
                passed = new Font(Font.FontFamily.UNDEFINED);
        PdfPTable table1 = getTable(TableWidthImpl.ONE_WIDTH),
                table2 = getTable(TableWidthImpl.FOUR_WIDTH),
                table3 = getTable(TableWidthImpl.TWO_WIDTH),
                table4 = getTable(TableWidthImpl.FOUR_WIDTH),
                table5 = getTable(TableWidthImpl.FOUR_WIDTH);
        PdfPCell cellTitle = getCell("Evidências de teste"),
                cellSystemName = getCell("Sistema"),
                cellSystemVersion = getCell("Versão"),
                cellTestName = getCell("Teste"),
                cellTestNumber = getCell("ID"),
                cellTestCycle = getCell("Ciclo"),
                cellTesterName = getCell("Executor"),
                cellTestStatus = getCell("Situação"),
                cell1 = getCell(evidence.getSystemName()),
                cell2 = getCell(evidence.getSystemVersion()),
                cell3 = getCell(evidence.getTestName()),
                cell4 = getCell(evidence.getTestId()),
                cell5 = getCell(evidence.getTestCycle()),
                cell6 = getCell(evidence.getTesterName()),
                cell7;
        failed.setColor(BaseColor.RED);
        passed.setColor(BaseColor.GREEN);
        setCellHorizontalAlignment(Arrays.asList(cellTitle, cellSystemName, cellSystemVersion, cellTestName, cellTestNumber, cellTestCycle, cellTesterName, cellTestStatus), 1);
        setCellBackgroundColor(Arrays.asList(cellTitle, cellSystemName, cellSystemVersion, cellTestName, cellTestNumber, cellTestCycle, cellTesterName, cellTestStatus), new BaseColor(210, 210, 210));
        if (evidence.getTestStatus().equalsIgnoreCase("passed")) {
            cell7 = getCell(evidence.getTestStatus(), passed);
        } else {
            cell7 = getCell(evidence.getTestStatus(), failed);
        }
        setCellHorizontalAlignment(Arrays.asList(cell1, cell2, cell3, cell4, cell5, cell6, cell7), 1);
        insertCell(table1, Arrays.asList(cellTitle, cellSystemName, cell1, cellSystemVersion, cell2, cellTestName, cell3, cellTestNumber, cell4, cellTestCycle, cell5, cellTesterName, cell6, cellTestStatus, cell7));
        insertTable(document, Arrays.asList(table1, table2, table3, table4, table5));
    }

    private static Document createDocument(final String pdfFileName) throws DocumentException, FileNotFoundException {
        return getDocument(pdfFileName);
    }
}
