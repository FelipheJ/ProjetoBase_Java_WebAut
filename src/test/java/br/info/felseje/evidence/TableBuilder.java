package br.info.felseje.evidence;

import java.util.Map;
import com.itextpdf.text.pdf.PdfPTable;
import br.info.felseje.evidence.interfaces.Table;
import br.info.felseje.evidence.interfaces.TableWidth;

import static br.info.felseje.evidence.utils.PDFUtils.*;

public class TableBuilder implements Table {

    public TableBuilder(Map<String, String> tableContent) {
        this.tableContent = tableContent;
    }

    private Map<String, String> tableContent;

    @Override
    public PdfPTable getTable(TableWidth tableWidth) {
        PdfPTable table = tableWidth.getWidth();
        tableContent.forEach((k, v) -> {
            table.addCell(getParagraph(k, 1));
            table.addCell(v);
        });
        return table;
    }
}
