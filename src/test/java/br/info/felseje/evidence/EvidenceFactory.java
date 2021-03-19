package br.info.felseje.evidence;

import io.cucumber.java.Status;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.DocumentException;
import br.info.felseje.evidence.model.Evidence;
import br.info.felseje.evidence.enums.TableWidthImpl;

import static br.info.felseje.evidence.utils.PDFUtils.*;
import static br.info.felseje.evidence.utils.EvidenceUtils.*;

/**
 * Build evidence files.
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class EvidenceFactory {

    private static Document document;

    public static void main(String... args) throws DocumentException, IOException {
        build(new Evidence()
                .setTestName("Teste Bonitão")
                .setTestIdNumber("ID001")
                .setTestCycle("Sprint 1")
                .setTesterName("Feliphe Jesus")
                .setTestRuntime("2 minutos")
                .setTestDate("10/01/2021")
                .setTestStatus(Status.PASSED)
        );
    }

    public static void build(Evidence evidence) throws DocumentException, IOException {
        createEvidenceFolder(evidence.getTestStatus());
        document = getDocument(getEvidencePath(evidence.getTestStatus()) + getEvidenceFileName(evidence));
        document.open();
        createHeader(evidence);
//        insertScreenshots(evidence);
        document.close();
    }

    private static void createHeader(Evidence evidence) throws DocumentException {
        PdfPTable table1, table2;
        table1 = getTable(TableWidthImpl.FIELD_AND_VALUE, getFieldAndValueList(evidence, 1));
        table2 = getTable(TableWidthImpl.TWO_FIELDS_TWO_VALUES, getFieldAndValueList(evidence, 2));
        document.add(table1);
        document.add(table2);
    }

    private static Map<String, String> getFieldAndValueList(Evidence evidence, int type) {
        Map<String, String> map = new HashMap<>();
        if (type == 1) {
            map.put("Teste", evidence.getTestName());
        } else if (type == 2) {
            map.put("Número", evidence.getTestIdNumber());
            map.put("Ciclo", evidence.getTestCycle());
            map.put("Executor", evidence.getTesterName());
            map.put("Duração", evidence.getTestRuntime());
            map.put("Data", evidence.getTestDate());
            map.put("Situação", evidence.getTestStatus().name());
        } else throw new IllegalArgumentException("Undefined Type!");
        return map;
    }
}
