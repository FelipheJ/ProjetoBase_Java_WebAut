package br.info.felseje.evidence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.info.felseje.evidence.enums.TableBuilderImpl;
import br.info.felseje.evidence.enums.TableWidthImpl;
import br.info.felseje.evidence.interfaces.TableWidth;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import br.info.felseje.evidence.model.Evidence;
import br.info.felseje.evidence.enums.TableModelImpl;

import static br.info.felseje.evidence.utils.PDFUtils.*;
import static br.info.felseje.evidence.utils.EvidenceUtils.*;

/**
 * Build evidence files.
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class EvidenceFactory {

    private static Document document;

    public static void build(Evidence evidence) throws DocumentException, IOException {
        createEvidenceFolder(evidence.getTestStatus());
        document = getDocument(getEvidencePath(evidence.getTestStatus()));
        document.open();
        createHeader(evidence);
    }

    private static void createHeader(Evidence evidence) {
        PdfPTable table1, table2;
        PdfPCell testName, testId, testCycle, testDate,
                testerName, testRuntime, testStatus;


    }
}
